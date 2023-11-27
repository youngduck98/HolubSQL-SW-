package com.holub.application.domain.book;

import java.time.*;
import java.util.Arrays;
import java.util.List;

public class Book {

    private Integer uuid;                   // 고유 번호
    private String title;                   // 제목
    private String author;                  // 작가
    private LocalDate publicationDate;      // 출간일
    private LocalDate registrationDate;     // 등록일
    private Integer quantity;               // 수량
    private Location location;              // 위치 -> 서울, 안성
    private CheckOutState checkOutState;    // 상태 -> 대여 가능, 대여 불가, 삭제
    private String genre;                   // 장르

    public Book(Integer uuid, String title, String author, LocalDate publicationDate, LocalDate registrationDate, Integer quantity, Location location, CheckOutState checkOutState, String genre) {
        this.uuid = uuid;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.registrationDate = registrationDate;
        this.quantity = quantity;
        this.location = location;
        this.checkOutState = checkOutState;
        this.genre = genre;
    }

    public Book(String title, String author, LocalDate publicationDate, LocalDate registrationDate, Integer quantity, Location location, CheckOutState checkOutState, String genre) {
        this.uuid = -1;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.registrationDate = registrationDate;
        this.quantity = quantity;
        this.location = location;
        this.checkOutState = checkOutState;
        this.genre = genre;
    }

    public List<Object> toList() {
        return Arrays.asList(
            uuid, title, author, publicationDate, registrationDate, quantity, location, checkOutState, genre
        );
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
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
