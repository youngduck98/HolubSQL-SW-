package com.holub.application.domain.checkout;

import java.time.*;
import java.util.Arrays;
import java.util.List;

public class CheckOut {

    private Integer uuid;
    private Integer memberUuid;
    private Integer bookUuid;
    private LocalDate rentalDate;
    private LocalDate dueDate;

    public CheckOut(Integer uuid, Integer memberUuid, Integer bookUuid, LocalDate rentalDate, LocalDate dueDate) {
        this.uuid = uuid;
        this.memberUuid = memberUuid;
        this.bookUuid = bookUuid;
        this.rentalDate = rentalDate;
        this.dueDate = dueDate;
    }

    public CheckOut(Integer memberUuid, Integer bookUuid, LocalDate rentalDate, LocalDate dueDate) {
        this.uuid = -1;
        this.memberUuid = memberUuid;
        this.bookUuid = bookUuid;
        this.rentalDate = rentalDate;
        this.dueDate = dueDate;
    }

    public CheckOut(List<Object> row) {
        this.uuid = (Integer) row.get(0);
        this.memberUuid = (Integer) row.get(1);
        this.bookUuid = (Integer) row.get(2);
        this.rentalDate = (LocalDate) row.get(3);
        this.dueDate = (LocalDate) row.get(4);
    }

    public List<Object> toList() {
        return Arrays.asList(
                uuid, memberUuid, bookUuid, rentalDate, dueDate
        );
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Integer getMemberUuid() {
        return memberUuid;
    }

    public void setMemberUuid(Integer memberUuid) {
        this.memberUuid = memberUuid;
    }

    public Integer getBookUuid() {
        return bookUuid;
    }

    public void setBookUuid(Integer bookUuid) {
        this.bookUuid = bookUuid;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
