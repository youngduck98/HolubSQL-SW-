package com.holub.application.service.book;

import com.holub.application.dao.BookDao;
import com.holub.application.dao.MemberDao;
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
    private final BookDao bookDao;
    private final MemberDao memberDao;

    private BookServiceImpl(BookDao bookDao, MemberDao memberDao){
        this.bookDao = bookDao;
        this.memberDao = memberDao;
    }

    public static BookService getInstance(BookDao bookDao, MemberDao memberDao){
        if (instance == null) {
            instance = new BookServiceImpl(bookDao, memberDao);
        }
        return instance;
    }

    @Override
    public List<Book> getBookList(List<Integer> uuidList, String[] callName, int[] asc) {
        List<Book> list = new ArrayList<>();

        for (Object o : bookDao.selectTable(uuidList, callName, asc)) {
            list.add((Book) o);
        }

        return list;
    }

    @Override
    public void addBook(Grant grant, Book bookInfo) {
        if (bookInfo.getUuid() == -1 && grant == Grant.Manager){
            bookDao.insertTable(Arrays.asList(new Book[] {bookInfo}));
        }
    }

    @Override
    public void deleteBook(Model model) throws IOException {
        Grant grant = getMyGrant(model);
        if (grant == Grant.Manager) {
            Book book = getBookList(model).get(0);
            book.setCheckOutState(CheckOutState.Deleted);
            bookDao.updateTable(book);
        }

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
        return bookDao.getTablesGenre();
    }

    @Override
    public List<Book> getOnlySpecialGenre(Model model) {
        String genre = (String) model.getAttribute("genre");
        model.clearAttribute();

        return bookDao.getGenresBook(genre);
    }

    private Grant getMyGrant(Model model) {

        if (!model.containsAttribute("myInfo"))
            return Grant.None;

        Integer myUuid = (Integer) model.getAttribute("myInfo");

        List<Object> memberList = memberDao.selectTable(
                Arrays.asList(new Integer[] {myUuid}), null, null);
        if (!memberList.isEmpty()){
            Member myMember = (Member) memberList.get(0);
            return myMember.getGrant();
        }

        return Grant.None;
    }
}
