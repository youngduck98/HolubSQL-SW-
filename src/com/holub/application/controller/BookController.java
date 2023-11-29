package com.holub.application.controller;

import com.holub.application.dao.BookDao;
import com.holub.application.dao.MemberDao;
import com.holub.application.domain.book.Book;
import com.holub.application.domain.member.Grant;
import com.holub.application.model.Model;
import com.holub.application.model.vtc.BookVTC;
import com.holub.application.service.book.BookService;
import com.holub.application.service.book.BookServiceImpl;
import com.holub.application.view.View;

import java.util.List;

public class BookController {
    private static BookController bc;
    private static BookService bookService;
    private static View view;

    private BookController(BookDao bookDao, MemberDao memberDao){
        bookService = BookServiceImpl.getInstance(bookDao, memberDao);
    }

    public BookController getInstance(BookDao bookDao, MemberDao memberDao){
        if(bc == null){
            bc = new BookController(bookDao, memberDao);
        }
        return bc;
    }

    public void setView(View view){
        BookController.view = view;
    }

    public View sortBookShelf(Model model){
        if(model.getGrant() == Grant.None)
            throw new IllegalArgumentException("권한 없음");
        BookVTC input = (BookVTC) model.getInput();
        //int index =
        bookService.sortBook(input.getColName(), input.getAsc());
        return view;
    }

    public View searchBookByUid(Model model){
        if(model.getGrant() == Grant.None)
            throw new IllegalArgumentException("권한 없음");
        BookVTC input = (BookVTC) model.getInput();
        List<Book> bookList = bookService.getBookListByUid(input.getUuidList());
        //TODO
        //transfer bookList to view
        return view;
    }

    public View searchBookByName(Model model){
        if(model.getGrant() == Grant.None)
            throw new IllegalArgumentException("권한 없음");
        BookVTC input = (BookVTC) model.getInput();
        List<Book> bookList = bookService.getBookByName(input.getBookName());
        //TODO
        //add bookList to view
        return view;
    }

    public View searchBookByGenre(Model model){
        if(model.getGrant() == Grant.None)
            throw new IllegalArgumentException("권한 없음");
        BookVTC input = (BookVTC) model.getInput();
        int index = model.getIndex();
        int size = model.getSize();
        List<Book> bookList = bookService.getOnlySpecialGenre(input.getBookName());
        //TODO
        //add bookList to view
        return view;
    }
}
