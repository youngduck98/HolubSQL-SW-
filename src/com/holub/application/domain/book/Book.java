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
    private Integer numOfCheckout;          // 빌린 책 횟수

    public Book(Integer uuid, String title, String author, LocalDate publicationDate, LocalDate registrationDate, Integer quantity, Location location, CheckOutState checkOutState, String genre, Integer numOfCheckout) {
        this.uuid = uuid;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.registrationDate = registrationDate;
        this.quantity = quantity;
        this.location = location;
        this.checkOutState = checkOutState;
        this.genre = genre;
        this.numOfCheckout = numOfCheckout;
    }

    public Book(List<Object> row){
        this.uuid = Integer.parseInt(row.get(0).toString());
        this.title = (String) row.get(1);
        this.author = (String) row.get(2);
        this.publicationDate = LocalDate.parse(row.get(3).toString());
        this.registrationDate = LocalDate.parse(row.get(4).toString());
        this.quantity = Integer.parseInt(row.get(5).toString());
        this.location = Location.valueOf(row.get(6).toString());
        this.checkOutState = CheckOutState.valueOf(row.get(7).toString());
        this.genre = (String) row.get(8);
        this.numOfCheckout = Integer.parseInt(row.get(9).toString());
    }

    public Book(String title, String author, LocalDate publicationDate, LocalDate registrationDate, Integer quantity, Location location, CheckOutState checkOutState, String genre, Integer numOfCheckout) {
        this.uuid = -1;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.registrationDate = registrationDate;
        this.quantity = quantity;
        this.location = location;
        this.checkOutState = checkOutState;
        this.genre = genre;
        this.numOfCheckout = numOfCheckout;
    }

    public List<Object> toList() {
        return Arrays.asList(
            uuid, title, author, publicationDate, registrationDate, quantity, location, checkOutState, genre, numOfCheckout
        );
    }

    public static String[] getColumnames(){
        return new String[]{"uuid", "title", "author", "publicationDate", "registrationDate"
                , "quantity", "location", "checkOutState", "genre", "numOfCheckout"};
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

    public Integer getNumOfCheckout() {
        return numOfCheckout;
    }

    public void setNumOfCheckout(Integer numOfCheckout) {
        this.numOfCheckout = numOfCheckout;
    }
}
