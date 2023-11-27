package com.holub.domain.book;

import java.time.*;

public class Book {

    private String uuid;                       // 고유 번호
    private String title;                   // 제목
    private String author;                  // 작가
    private LocalDate publicationDate;      // 출간일
    private LocalDate registrationDate;     // 등록일
    private Integer quantity;                   // 수량
    private Location location;              // 위치 -> 서울, 안성
    private CheckOutState checkOutState;    // 상태 -> 대여 가능, 대여 불가, 삭제

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CheckOutState getCheckOutState() {
        return checkOutState;
    }

    public void setCheckOutState(CheckOutState checkOutState) {
        this.checkOutState = checkOutState;
    }
}
