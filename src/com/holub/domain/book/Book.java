package com.holub.domain.book;

import java.time.*;

public class Book {

    int uuid;                       // 고유 번호
    String title;                   // 제목
    String author;                  // 작가
    LocalDate publicationDate;      // 출간일
    LocalDate registrationDate;     // 등록일
    int quantity;                   // 수량
    Location location;              // 위치 -> 서울, 안성
    CheckOutState checkOutState;    // 상태 -> 대여 가능, 대여 불가

}
