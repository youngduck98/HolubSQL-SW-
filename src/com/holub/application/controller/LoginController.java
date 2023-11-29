package com.holub.application.controller;

import com.holub.application.model.LoginToken;
import com.holub.application.model.vtc.LoginVTC;
import com.holub.application.service.login.LoginService;
import com.holub.application.service.login.LoginServiceImpl;
import com.holub.application.view.LoginView;

public class LoginController {

    public static LoginController loginController;
    LoginView view;
    LoginService loginService;

    private LoginController(LoginView view, LoginService loginService){
        this.loginService = loginService;
        this.view = view;
    }

    public static LoginController getInstance(LoginView view){
        if(loginController == null)
            loginController = new LoginController(view, LoginServiceImpl.getInstance());
        return loginController;
    }

    public LoginToken login(){
        LoginVTC vtc = view.getIdPW();
        Integer userUid;
        try {
            userUid = loginService.login(vtc);
            return new LoginToken(userUid, vtc.getGrant());
        }
        catch (IllegalArgumentException e){
            login();
        }
        return login();
    }
}
