package com.holub.application.model.vtc;

import com.holub.application.domain.book.Book;

public class BookRegisterVTC implements VTC{

    private final Book book;

    public BookRegisterVTC(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}
