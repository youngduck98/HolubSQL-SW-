package com.holub.application.controller;

import com.holub.application.view.MainManagerView;
import com.holub.application.view.MainMemberView;

public class ManagerController {
    private static ManagerController managerController;
    private MainManagerView mainManagerView;
    private ManagerController(MainManagerView mainManagerView){
        this.mainManagerView = mainManagerView;
    }
    public static ManagerController getInstance(MainManagerView mainManagerView){
        if(managerController == null)
            managerController = new ManagerController(mainManagerView);
        return managerController;
    }

}
