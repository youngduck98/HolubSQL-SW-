package com.holub.application.view;

import com.holub.application.domain.book.Book;
import com.holub.application.domain.book.CheckOutState;
import com.holub.application.domain.book.Location;
import com.holub.application.util.InputScanner;

import java.time.LocalDate;

public class BookUpdateView {

    private final InputScanner scanner = InputScanner.getInstance();

    private final Book selectedBookInfo;

    public BookUpdateView(Book book) {
        selectedBookInfo = book;
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

    public Book execute() {
        // TODO -> validation 필요
        while(true) {
            System.out.println("수정할 책 컬럼 입력 <!모두 소문자 입력!> (0)나가기");
            for (String str : Book.getColumnames()) {
                if(str.equals("uuid"))
                    continue;
                System.out.print(str + "\t");
            }
            System.out.println();
            System.out.println(selectedBookInfo.toList());

            String selectedMenu = getString("columName");
            if (selectedMenu.equals("0"))
                return selectedBookInfo;
            switch(selectedMenu) {
                case "title":
                    String title = getString("title");
                    selectedBookInfo.setTitle(title);
                    break;
                case "author":
                    String author = getString("author");
                    selectedBookInfo.setAuthor(author);
                    break;
                case "quantity":
                    Integer quantity = getInteger("quantity");
                    selectedBookInfo.setQuantity(quantity);
                    break;
                case "publicationDate":
                    int publicationYear = getInteger("publication Year");
                    int publicationMonth = getInteger("publication Month");
                    int publicationDay = getInteger("publication Day");
                    LocalDate publicationDate = LocalDate.of(publicationYear, publicationMonth, publicationDay);
                    selectedBookInfo.setPublicationDate(publicationDate);
                    break;
                case "checkoutstate":
                    System.out.println("대여 가능 여부를 선택해 주세요 (1)대여 가능 (2)대여 불가");
                    String temp = getString("state");
                    CheckOutState checkOutState;
                    if (temp.equals("1")){
                        checkOutState = CheckOutState.Available;
                    } else {
                        checkOutState = CheckOutState.Unavailable;
                    }
                    selectedBookInfo.setCheckOutState(checkOutState);
                    break;
                case "location":
                    System.out.println("지역을 선택해 주세요 (1)서울 (2)안성");
                    temp = getString("location");
                    Location location;
                    if (temp.equals("1"))
                        location = Location.Seoul;
                    else
                        location = Location.Anseong;
                    selectedBookInfo.setLocation(location);
                    break;
                case "genre":
                    String genre = getString("genre");
                    selectedBookInfo.setTitle(genre);
                    break;
                default:
                    System.out.println("<ERROR!> 다시 입력해 주세요!");
            }
        }
    }

}
