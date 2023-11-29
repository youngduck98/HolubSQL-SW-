package com.holub.application.model.vtc;

import java.time.LocalDate;

public class CheckOutManagerVTC implements VTC{

    private final String menu;
    private final Integer updateCheckOutUuid;
    private final LocalDate updateDueDate;

    public CheckOutManagerVTC(String menu) {
        this.menu = menu;
        this.updateCheckOutUuid = 0;
        updateDueDate = null;
    }

    public CheckOutManagerVTC(String menu, Integer updateCheckOutUuid, LocalDate updateDueDate) {
        this.menu = menu;
        this.updateCheckOutUuid = updateCheckOutUuid;
        this.updateDueDate = updateDueDate;
    }

    public String getMenu() {
        return menu;
    }

    public Integer getUpdateCheckOutUuid() {
        return updateCheckOutUuid;
    }

    public LocalDate getUpdateDueDate() {
        return updateDueDate;
    }
}
