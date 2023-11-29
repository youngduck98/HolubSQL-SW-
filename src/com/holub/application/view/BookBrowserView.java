package com.holub.application.view;

import com.holub.application.model.Model;
import com.holub.application.model.ctv.CTV;
import com.holub.application.model.vtc.VTC;
import com.holub.application.util.InputScanner;

import java.util.List;

public class BookBrowserView extends View {

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

    public Integer getUserSelect(){
        System.out.println("(1)장르 검색 (2)Index 검색");
        String selectedMenu = getString();
        if(selectedMenu.equals("1") || selectedMenu.equals("2"))
            return Integer.parseInt(selectedMenu);
        return getUserSelect();
    }

    @Override
    public List<String> execute(Model model) {
        return null;
    }
}