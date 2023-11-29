package com.holub.application.controller;

import com.holub.application.dao.MemberDao;
import com.holub.application.view.MainMemberView;

public class MemberController {
    private static MemberController memberController;
    private MainMemberView mainMemberView;
    private MemberController(MainMemberView mainMemberView){
        this.mainMemberView = mainMemberView;
    }
    public static MemberController getInstance(MainMemberView mainMemberView){
        if(memberController == null)
            memberController = new MemberController(mainMemberView);
        return memberController;
    }

    public void execute1(){

    }
}
