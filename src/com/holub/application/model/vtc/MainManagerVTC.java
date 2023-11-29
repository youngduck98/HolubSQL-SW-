package com.holub.application.model.vtc;

import java.util.List;

public class MainManagerVTC implements VTC{

    private final String menu;
    private List<Object> addBookInfo;

    public MainManagerVTC(String menu) {
        this.menu = menu;
    }

    public String getMenu() {
        return menu;
    }

}
