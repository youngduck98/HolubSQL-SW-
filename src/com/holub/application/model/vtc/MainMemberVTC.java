package com.holub.application.model.vtc;

public class MainMemberVTC implements VTC{

    private final String menu;
    private Integer index;
    private Integer size;
    private Integer bookUuid;
    private String bookTitle;
    private String bookGenre;
    private String callName;
    private int asc;

    public MainMemberVTC(String menu) {
        this.menu = menu;
    }

    public String getMenu() {
        return menu;
    }

    public Integer getBookIndex() {
        return bookUuid;
    }

    public void setBookIndex(Integer bookIndex) {
        this.bookUuid = bookIndex;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getCallName() {
        return callName;
    }

    public void setCallName(String callName) {
        this.callName = callName;
    }

    public int getAsc() {
        return asc;
    }

    public void setAsc(int asc) {
        this.asc = asc;
    }
}
