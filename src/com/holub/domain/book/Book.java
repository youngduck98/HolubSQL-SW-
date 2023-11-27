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

}
