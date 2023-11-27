package com.holub.application.service.book;

import com.holub.application.dao.BookDao;
import com.holub.application.dao.Dao;
import com.holub.application.domain.book.Book;
import com.holub.application.domain.book.CheckOutState;
import com.holub.application.domain.member.Grant;
import com.holub.application.model.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    public List<Book> getBookList(Model model) {
        List<Book> list = new ArrayList<>();
        
        for (Object o : dao.selectTable(uuids, callName, asc)) {
            list.add((Book) o);
        }
        return list;
    }

    @Override
    public void addBook(Model model) {
        if (grant == Grant.Manager) {
            dao.insertTable(Arrays.asList(new Book[] {bookInfo}));
        }
    }

    @Override
    public void deleteBook(Model model) throws IOException {
        if (grant == Grant.Manager) {
            Book book = getBookList(Arrays.asList(new Integer[] {uuid}), null, null).get(0);
            book.setCheckOutState(CheckOutState.Deleted);
            dao.updateTable(book);
        }
    }

    @Override
    public void modifyBookInfo(Model model) throws IOException{
        if (grant == Grant.Manager) {
            dao.updateTable(bookInfo);
        }
    }

    @Override
    public List<String> getBookGenre() {
        // TODO
        return ((BookDao)dao).getTablesGenre();
    }

    @Override
    public List<Book> getOnlySpecialGenre(Model model) {
        // TODO
        String genre = (String) model.getAttribute("genre");
        return ((BookDao)dao).getGenresBook(genre);
    }
}
