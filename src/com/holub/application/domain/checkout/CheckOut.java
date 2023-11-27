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
