package com.holub.application.model.vtc;

public class BookBrowserVTC implements VTC{

    private final String callName;
    private final String genre;
    private final int asc;

    public BookBrowserVTC(String callName, String genre) {
        this.callName = callName;
        this.genre = genre;
        this.asc = 1;
    }

    public BookBrowserVTC(String callName) {
        this.callName = callName;
        this.genre = "";
        this.asc = 1;
    }

    public String getCallName() {
        return callName;
    }

    public int getAsc() {
        return asc;
    }

    public String getGenre{
        return genre;
    }
}
