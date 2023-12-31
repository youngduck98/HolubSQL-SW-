package com.holub.application.view;

import com.holub.application.domain.book.Book;
import com.holub.application.util.InputScanner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class MainView {

    private final InputScanner scanner = InputScanner.getInstance();

    public void showMenu() {
        System.out.println("(0)로그 아웃 (1)내 정보 (3)대출 정보/반납 (4)책 검색 (5)책 정렬 (6)책 대출");
        System.out.println("(Q)이전 페이지 (W)다음 페이지 ");
    }

    public String getBookName(){
        return getString("bookName");
    }

    // 책 리스트 출력
    public void printBookMenu(Book book){
        for(String str: Book.getColumnames()){
            System.out.printf("%17s", str);
        }
        System.out.println();
    }

    public void printBook(Book book){
        for(Object field: book.toList()){
            System.out.printf("%17s", field.toString());
        }
        System.out.println();
    }

    // 책 리스트 출력
    public void showBookList(List<Book> bookList) {
        if(bookList == null || bookList.isEmpty())
            return;
        printBookMenu(bookList.get(0));
        for(Book book: bookList){
            printBook(book);
        }
    }

    public String getMenu(){
        String input = getString("menuNumber");
        if(isSelectedMenuValid(input))
            return input;
        return getMenu();
    }

    // String 입력 받기
    public String getString(String str) {
        System.out.print(str + ">> ");
        return scanner.inputString();
    }

    public Integer getInteger(String str) {
        System.out.print(str + ">> ");
        return scanner.inputInteger();
    }

    public void print(String str){
        System.out.println(str);
    }

    public String getStringInList(List<String> list){
        String str = getString("colName you want to sort");
        if(!list.contains(str))
            return getStringInList(list);
        return str;
    }

    public Integer getIntegerInList(List<Integer> list){
        Integer n = getInteger("uid in list");
        if(!list.contains(n))
            return getIntegerInList(list);
        return n;
    }

    public String getColName() {
        List<String> fieldNames = new ArrayList<>();
        for(String str: Book.getColumnames()){
            System.out.print(str + " ");
            fieldNames.add(str);
        }
        System.out.println();
        return getStringInList(fieldNames);
    }

    public int getAsc(){
        String asc = getString("input ascend = 1/descend = -1");
        if(!(asc.equals("1") || asc.equals("-1")))
            return getAsc();
        return Integer.parseInt(asc);
    }

    // TODO -> 이거 따로 빼는게 좋을것 같음 중복 된다.
    public boolean isSelectedMenuValid(String selectedMenu) {
        switch(selectedMenu){
            case "1": case "3":
            case "4": case "5": case "Q":
            case "W": case "q": case "w":
            case "6": case "0":
                return true;
            default:
                return false;
        }
    }
}
