package com.holub.application.controller;

import com.holub.application.domain.member.Member;
import com.holub.application.model.LoginToken;
import com.holub.application.model.vtc.LoginVTC;
import com.holub.application.service.login.LoginService;
import com.holub.application.service.login.LoginServiceImpl;
import com.holub.application.service.member.MemberService;
import com.holub.application.service.member.MemberServiceImpl;
import com.holub.application.view.LoginView;
import com.holub.application.view.SignUpView;

public class LoginController {
    public static LoginController loginController;
    LoginView view;
    LoginService loginService;
    MemberService memberService = MemberServiceImpl.getInstance();
    SignUpView signUpView = new SignUpView();

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
        String mode = view.getSignupOrLogin();
        if(mode.equals("1")){
            try {
                Member member = signUpView.execute();
                memberService.addMember(member);
                mode = "2";
            }
            catch (Exception e){
                System.out.println("error at add member" + e);
                login();
            }
        }
        if(mode.equals("2")){
            LoginVTC vtc = view.getIdPW();
            Integer userUid;
            try {
                userUid = loginService.login(vtc);
                return new LoginToken(userUid, vtc.getGrant());
            }
            catch (IllegalArgumentException e){
                System.out.println("something error occured: " + e);
                return login();
            }
        }
        throw new IllegalArgumentException("no mode like that");
    }
}
