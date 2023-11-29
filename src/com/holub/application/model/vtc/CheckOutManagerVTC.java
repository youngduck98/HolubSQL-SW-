package com.holub.application.model.vtc;

import java.time.LocalDate;

public class CheckOutManagerVTC implements VTC{
    private final Integer updateCheckOutUuid;
    private final LocalDate updateDueDate;

    public CheckOutManagerVTC() {
        this.updateCheckOutUuid = 0;
        updateDueDate = null;
    }

    public CheckOutManagerVTC(Integer updateCheckOutUuid, LocalDate updateDueDate) {
        this.updateCheckOutUuid = updateCheckOutUuid;
        this.updateDueDate = updateDueDate;
    }


    public Integer getUpdateCheckOutUuid() {
        return updateCheckOutUuid;
    }

    public LocalDate getUpdateDueDate() {
        return updateDueDate;
    }
}
