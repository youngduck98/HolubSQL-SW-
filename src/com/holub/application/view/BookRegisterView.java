package com.holub.application.view;

import com.holub.application.domain.book.Book;
import com.holub.application.domain.book.CheckOutState;
import com.holub.application.domain.book.Location;
import com.holub.application.model.ctv.CTV;
import com.holub.application.model.vtc.BookRegisterVTC;
import com.holub.application.model.vtc.BookSortingVTC;
import com.holub.application.model.vtc.VTC;
import com.holub.application.util.InputScanner;

import java.time.LocalDate;

public class BookRegisterView implements View{

    private final InputScanner scanner = InputScanner.getInstance();
    private final CTV ctv;

    public BookRegisterView(CTV ctv) {
        this.ctv = ctv;
    }

    // String 입력 받기
    public String getString(String str) {
        System.out.print(str + " >> ");
        return scanner.inputString();
    }

    public Integer getInteger(String str) {
        System.out.print(str + " >> ");
        return scanner.inputInteger();
    }

    @Override
    public VTC execute() {
        // TODO -> validation 필요
        System.out.println("책 등록을 시작합니다.");
        String title = getString("title");
        String author = getString("author");
        int publicationYear = getInteger("publication Year");
        int publicationMonth = getInteger("publication Month");
        int publicationDay = getInteger("publication Day");
        LocalDate publicationDate = LocalDate.of(publicationYear, publicationMonth, publicationDay);
        LocalDate registrationDate = LocalDate.now();
        Integer quantity = getInteger("quantity");
        System.out.println("대여 가능 여부를 선택해 주세요 (1)대여 가능 (2)대여 불가");
        String temp = getString("state");
        CheckOutState checkOutState;
        if (temp.equals("1")){
            checkOutState = CheckOutState.Available;
        } else {
            checkOutState = CheckOutState.Unavailable;
        }
        // TODO genre 출력 + genre 선택
        String genre = getString("TEMP");
        System.out.println("지역을 선택해 주세요 (1)서울 (2)안성");
        temp = getString("location");
        Location location;
        if (temp.equals("1"))
            location = Location.Seoul;
        else
            location = Location.Anseong;

        Book book = new Book(title, author, publicationDate, registrationDate, quantity, location, checkOutState, genre);

        return new BookRegisterVTC(book);
    }

}
