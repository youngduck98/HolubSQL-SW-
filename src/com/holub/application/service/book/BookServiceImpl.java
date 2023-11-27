package com.holub.application.service.book;

import com.holub.application.dao.Dao;
import com.holub.application.domain.book.Book;
import com.holub.application.domain.member.Grant;

import java.util.List;

public class BookServiceImpl implements BookService {

    private static BookService instance;
    private final Dao dao;

    private BookServiceImpl(Dao dao){
        this.dao = dao;
    }

    public static BookService getInstance(Dao dao){
        if (instance == null) {
            instance = new BookServiceImpl(dao);
        }
        return instance;
    }

    @Override
    public void getBookList(Grant grant, List<Integer> uuids) {

    }

    @Override
    public void addBook(Grant grant, Book bookInfo) {

    }

    @Override
    public void deleteBook(Grant grant, Integer uuid) {

    }

    @Override
    public void modifyBookInfo(Grant grant, Integer uuid, Book bookInfo) {

    }

    @Override
    public void getBookGenre(Grant grant) {

    }

    @Override
    public List<Book> getOnlySpecialGenre(Grant grant, String genre) {
        return null;
    }
}
