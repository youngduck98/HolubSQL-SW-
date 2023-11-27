package com.holub.application.service.book;

import com.holub.application.dao.BookDao;
import com.holub.application.dao.Dao;
import com.holub.application.domain.book.Book;
import com.holub.application.domain.book.CheckOutState;
import com.holub.application.domain.member.Grant;
import com.holub.application.domain.member.Member;
import com.holub.application.model.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookServiceImpl implements BookService {

    private static BookService instance;
    private final Dao bookDao;
    private final Dao memberDao;

    private BookServiceImpl(Dao bookDao, Dao memberDao){
        this.bookDao = bookDao;
        this.memberDao = memberDao;
    }

    public static BookService getInstance(Dao bookDao, Dao memberDao){
        if (instance == null) {
            instance = new BookServiceImpl(bookDao, memberDao);
        }
        return instance;
    }

    @Override
    public List<Book> getBookList(Model model) {
        List<Book> list = new ArrayList<>();

        List<Integer> uuidList = (List<Integer>) model.getAttribute("uuidList");
        String[] callName = (String[]) model.getAttribute("callName");
        int[] asc = (int[]) model.getAttribute("asc");

        for (Object o : bookDao.selectTable(uuidList, callName, asc)) {
            list.add((Book) o);
        }

        model.clearAttribute();
        return list;
    }

    @Override
    public void addBook(Model model) {
        Grant grant = getMyGrant(model);
        Book bookInfo = (Book) model.getAttribute("addBookInfo");

        if (bookInfo.getUuid() == -1 && grant == Grant.Manager){
            bookDao.insertTable(Arrays.asList(new Book[] {bookInfo}));
        }

        model.clearAttribute();
    }

    @Override
    public void deleteBook(Model model) throws IOException {
        Grant grant = getMyGrant(model);

        if (grant == Grant.Manager) {
            Book book = getBookList(model).get(0);
            book.setCheckOutState(CheckOutState.Deleted);
            bookDao.updateTable(book);
        }

        model.clearAttribute();
    }

    @Override
    public void modifyBookInfo(Model model) throws IOException{
        Grant grant = getMyGrant(model);
        Book bookInfo = (Book) model.getAttribute("modifyBookInfo");

        if (grant == Grant.Manager) {
            bookDao.updateTable(bookInfo);
        }

        model.clearAttribute();
    }

    @Override
    public List<String> getBookGenre() {
        return ((BookDao)bookDao).getTablesGenre();
    }

    @Override
    public List<Book> getOnlySpecialGenre(Model model) {
        String genre = (String) model.getAttribute("genre");
        model.clearAttribute();
        return ((BookDao)bookDao).getGenresBook(genre);
    }

    private Grant getMyGrant(Model model) {
        Integer myUuid = (Integer) model.getAttribute("myInfo");
        Member myMember = (Member) memberDao.selectTable(
                Arrays.asList(new Integer[] {myUuid}), null, null).get(0);
        return myMember.getGrant();
    }
}
