package com.holub.application.controller;

import com.holub.application.view.MainMemberView;
import com.holub.application.view.MainView;

public class MainController {
    private static MainController mainController;
    private MainView mainView;
    private MainController(MainView MainView){
        this.mainView = MainView;
    }
    public static MainController getInstance(MainView MainView){
        if(mainController == null)
            mainController = new MainController(MainView);
        return mainController;
    }

    public void execute1(){
        mainView.showMenu();

    }
}
