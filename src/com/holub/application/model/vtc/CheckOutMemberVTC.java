package com.holub.application.model.vtc;

public class CheckOutMemberVTC implements VTC{

    private final String menu;
    private final Integer returnBookUuid;

    public CheckOutMemberVTC(String menu) {
        this.menu = menu;
        this.returnBookUuid = -1;
    }

    public CheckOutMemberVTC(String menu, Integer returnBookUuid) {
        this.menu = menu;
        this.returnBookUuid = returnBookUuid;
    }

    public String getMenu() {
        return menu;
    }

    public Integer getReturnBookUuid(){
        return returnBookUuid;
    }
}
