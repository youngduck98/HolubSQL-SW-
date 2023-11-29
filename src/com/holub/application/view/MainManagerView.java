package com.holub.application.view;

import com.holub.application.domain.book.Book;
import com.holub.application.util.InputScanner;

import java.lang.reflect.Field;
import java.util.List;

public class MainManagerView extends MainView{

    private final InputScanner scanner = InputScanner.getInstance();

    // 메뉴 출력
    public void showMenu() {
        System.out.println("(0)로그 아웃 (1)내 정보 (2)회원 정보/수정 (3)대출 정보/연장");
        System.out.println("(4)책 검색 (5)책 정렬 (6)책 등록 (7)책 수정");
        System.out.println("(Q)이전 페이지 (W)다음 페이지 ");
    }

    // String 입력 받기
    public String getString() {
        System.out.print(">> ");
        return scanner.inputString();
    }

    // TODO -> 이거 따로 빼는게 좋을것 같음 중복 된다.
    public boolean isSelectedMenuValid(String selectedMenu) {
        switch(selectedMenu){
            case "1": case "2": case "3":
            case "4": case "5": case "Q":
            case "W": case "q": case "w":
            case "6": case "7": case "0":
                return true;
            default:
                return false;
        }
    }
}
