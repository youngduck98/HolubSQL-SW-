package com.holub.application.view;

import com.holub.application.model.Model;
import com.holub.application.model.ctv.CTV;
import com.holub.application.util.InputScanner;

import java.util.List;

public class MainMemberView implements View{

    private final InputScanner scanner = InputScanner.getInstance();
    private final CTV ctv;

    public MainMemberView(CTV ctv) {
        this.ctv = ctv;
    }

    // 메뉴 출력
    public void showMenu() {
        System.out.println("(0)로그 아웃 (1)내 정보 (2)책 검색 (3)책 정렬 (4)대출 정보/반납 (5)책 대출");
        System.out.println("(Q)이전 페이지 (W)다음 페이지 ");
    }

    // 책 리스트 출력
    public void showBookList() {
        // TODO
        // CTV에서 Book 정보 찾아서 출력

    }

    // String 입력 받기
    public String getString() {
        System.out.print(">> ");
        return scanner.inputString();
    }

    public boolean isSelectedMenuValid(String selectedMenu) {
        switch(selectedMenu){
            case "1": case "2": case "3":
            case "4": case "5": case "Q":
            case "W": case "q": case "w":
            case "0":
                return true;
            default:
                return false;
        }
    }
}
