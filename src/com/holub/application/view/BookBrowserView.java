package com.holub.application.view;

import com.holub.application.util.InputScanner;

import java.util.List;

public class BookBrowserView{

    private final InputScanner scanner = InputScanner.getInstance();

    // String 입력 받기
    public String getString() {
        System.out.print(">> ");
        return scanner.inputString();
    }

    public Integer getUserSelect(){
        System.out.println("(1)장르 검색 (2)Index 검색");
        String selectedMenu = getString();
        if(selectedMenu.equals("1") || selectedMenu.equals("2"))
            return Integer.parseInt(selectedMenu);
        return getUserSelect();
    }
}
