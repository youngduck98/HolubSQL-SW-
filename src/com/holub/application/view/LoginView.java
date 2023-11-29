package com.holub.application.view;

import com.holub.application.domain.member.Grant;
import com.holub.application.model.vtc.LoginVTC;
import com.holub.application.util.InputScanner;

public class LoginView{
    private final InputScanner scanner = InputScanner.getInstance();

    // String 입력 받기
    public String getString(String str) {
        System.out.print(str + " >> ");
        return scanner.inputString();
    }

    public Grant getGrant(){
        String grant = getString("1: user 2: manger");
        if(!(grant.equals("1") || grant.equals("2"))){
            System.out.println("wrong input");
            return getGrant();
        }
        switch (grant){
            case "1": return Grant.Member;
            case "2": return Grant.Manager;
        }
        throw new IllegalArgumentException("unkown error");
    }

    public LoginVTC getIdPW(){
        Grant grant = getGrant();
        String id = getString("id");
        String pw = getString("pw");
        return new LoginVTC(id, pw, grant);
    }
}
