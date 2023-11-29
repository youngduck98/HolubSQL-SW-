package com.holub.application.view;

import com.holub.application.model.vtc.MainNoneVTC;
import com.holub.application.model.vtc.VTC;
import com.holub.application.util.InputScanner;

import java.util.List;

public class MainNoneView extends MainView{

    private final InputScanner scanner = InputScanner.getInstance();

    // 메뉴 출력
    public void showMenu() {
        System.out.println("(1)회원가입 (2)로그인 (3)책 검색");
        System.out.println("(Q)이전 페이지 (W)다음 페이지");
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