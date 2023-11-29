package com.holub.application.view;

import com.holub.application.model.ctv.CTV;
import com.holub.application.model.vtc.LoginVTC;
import com.holub.application.model.vtc.VTC;
import com.holub.application.util.InputScanner;

public class LoginView implements View{

    //TODO
    private final InputScanner scanner = InputScanner.getInstance();
    private final CTV ctv;

    public LoginView(CTV ctv) {
        this.ctv = ctv;
    }

    // String 입력 받기
    public String getString(String str) {
        System.out.print(str + " >> ");
        return scanner.inputString();
    }

    @Override
    public VTC execute() {
        // TODO -> validation 필요
        String id = getString("id");
        String password = getString("password");
        return new LoginVTC(id, password);
    }

}
