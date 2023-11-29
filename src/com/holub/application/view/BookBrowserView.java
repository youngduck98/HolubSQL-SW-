package com.holub.application.view;

import com.holub.application.model.ctv.CTV;
import com.holub.application.model.vtc.BookBrowserVTC;
import com.holub.application.model.vtc.BookSortingVTC;
import com.holub.application.model.vtc.VTC;
import com.holub.application.util.InputScanner;

public class BookBrowserView implements View{

    private final InputScanner scanner = InputScanner.getInstance();
    private final CTV ctv;

    public BookBrowserView(CTV ctv) {
        this.ctv = ctv;
    }

    // String 입력 받기
    public String getString() {
        System.out.print(">> ");
        return scanner.inputString();
    }

    @Override
    public VTC execute() {
        System.out.println("(1)장르 검색 (2)Index 검색");
        String selectedMenu = getString();
        String callName;
        if (selectedMenu.equals("1")){
            callName = "uuid";
            return new BookBrowserVTC(callName);
        } else {
            callName = "genre";
            // TODO -> 장르 검색시 장르 출력 + 입력 받아야함
            String genre = getString();
            return new BookBrowserVTC(callName, genre);
        }
    }

}
