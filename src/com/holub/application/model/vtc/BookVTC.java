package com.holub.application.model.vtc;

import java.util.List;

public class BookVTC implements VTC {
    String[] colName;
    int[] asc;
    List<Integer> uuidList;
    String bookName;
    String genre;

    public String getGenre(){
        return genre;
    }

    public String getBookName() {
        return bookName;
    }

    public String[] getColName() {
        return colName;
    }

    public int[] getAsc() {
        return asc;
    }

    public List<Integer> getUuidList() {
        return uuidList;
    }
}
