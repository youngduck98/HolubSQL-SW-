package com.holub.application.model.vtc;

public class MainNoneVTC implements VTC{

    private final String menu;
    private Integer index;
    private Integer size;

    public MainNoneVTC(String menu) {
        this.menu = menu;
    }

    public String getMenu() {
        return menu;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        if (index < 0)
            this.index = 0;
        else
            this.index = index;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
