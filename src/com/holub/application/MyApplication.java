package com.holub.application;

import com.holub.application.controller.*;
import com.holub.application.domain.member.Grant;
import com.holub.application.model.LoginToken;
import com.holub.application.service.login.LoginService;
import com.holub.application.service.login.LoginServiceImpl;
import com.holub.application.view.*;

public class MyApplication {
    LoginView loginview = new LoginView();
    LoginController loginController = LoginController.getInstance(loginview);
    Integer UserUid = null;
    public void execute(){

        // login 기능
        LoginView loginview = new LoginView();
        LoginController loginController = LoginController.getInstance(loginview);
        LoginToken loginToken = loginController.login();

        MainView mainView = null;
        //user, manager에 따라 구분
        if(loginToken.getGrant() == Grant.Manager){
            mainView = new MainManagerView();
        }
        if(loginToken.getGrant() == Grant.Member){
            mainView = new MainMemberView();
        }
        MainController mainController = MainController.getInstance(mainView);
        mainController.execute1();
    }
}
