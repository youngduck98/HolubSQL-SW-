package com.holub.application.view;

import com.holub.application.model.ctv.CTV;
import com.holub.application.model.vtc.BookSortingVTC;
import com.holub.application.model.vtc.MainManagerVTC;
import com.holub.application.model.vtc.VTC;
import com.holub.application.util.InputScanner;

public class BookSortingView implements View{

    private final InputScanner scanner = InputScanner.getInstance();
    private final CTV ctv;

    public BookSortingView(CTV ctv) {
        this.ctv = ctv;
    }

    // String 입력 받기
    public String getString() {
        System.out.print(">> ");
        return scanner.inputString();
    }

    @Override
    public VTC execute() {
        System.out.println("(1)Index로 정렬 (2)인기순으로 정렬");
        String selectedMenu = getString();
        System.out.println("(1)오름차순 (2)내림차순");
        String selectedAsc = getString();
        int asc = 0;
        if (selectedAsc.equals("1"))
            asc = 1;
        else {
            asc = -1;
        }

        return new BookSortingVTC(selectedMenu, asc);
    }

}
