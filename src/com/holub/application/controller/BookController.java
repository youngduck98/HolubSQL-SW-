package com.holub.application.controller;

import com.holub.application.dao.BookDao;
import com.holub.application.dao.MemberDao;
import com.holub.application.model.Model;
import com.holub.application.model.vtc.VTC;
import com.holub.application.service.book.BookService;
import com.holub.application.service.book.BookServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Mod;

public class BookController {
    private static BookController bc;
    private static BookService bookService;
    private BookController(BookDao bookDao, MemberDao memberDao){
        bookService = BookServiceImpl.getInstance(bookDao, memberDao);
    }

    public BookController getInstance(BookDao bookDao, MemberDao memberDao){
        if(bc == null){
            bc = new BookController(bookDao, memberDao);
        }
        return bc;
    }

    //public
}
