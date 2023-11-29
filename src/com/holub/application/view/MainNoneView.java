package com.holub.application.view;


import com.holub.application.model.Model;
import com.holub.application.model.ctv.CTV;
import com.holub.application.model.vtc.MainNoneVTC;
import com.holub.application.model.vtc.VTC;
import com.holub.application.util.InputScanner;

import java.util.List;

public class MainNoneView implements View {

    private final InputScanner scanner = InputScanner.getInstance();
    private final CTV ctv;

    public MainNoneView(CTV ctv) {
        this.ctv = ctv;
    }

    // 메뉴 출력
    public void showMenu() {
        System.out.println("(1)회원가입 (2)로그인 (3)책 검색");
        System.out.println("(Q)이전 페이지 (W)다음 페이지");
    }

    // 책 리스트 출력
    public void showBookList() {
        // TODO
    }

    public String getString() {
        System.out.print(">> ");
        return scanner.inputString();
    }

    public boolean isSelectedMenuValid(String selectedMenu) {
        switch(selectedMenu){
            case "1": case "2": case "3":
            case "Q": case "W": case "q":
            case "w":
                return true;
            default:
                return false;
        }
    }
}