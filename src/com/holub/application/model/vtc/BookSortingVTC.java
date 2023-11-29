package com.holub.application.model.vtc;

public class BookSortingVTC implements VTC{

    private final String menu;
    private final int asc;

    public BookSortingVTC(String menu, int asc) {
        this.menu = menu;
        this.asc = asc;
    }

    public String getMenu() {
        return menu;
    }

    public int getAsc() {
        return asc;
    }
}
