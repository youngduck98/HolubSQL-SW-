package com.holub.application.domain.checkout;

import java.util.Date;

public class CheckOut {

    private Integer uuid;
    private Integer memberUuid;
    private Integer bookUuid;
    private Date rentalDate;
    private Date dueDate;

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

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
