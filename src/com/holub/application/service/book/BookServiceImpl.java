package com.holub.application.service.book;

import com.holub.application.dao.BookDao;
import com.holub.application.dao.MemberDao;
import com.holub.application.domain.book.Book;
import com.holub.application.domain.book.CheckOutState;
import com.holub.application.domain.member.Grant;
import com.holub.application.domain.member.Member;

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

    public static BookService getInstance(){
        if(instance == null)
            throw new NullPointerException();
        return instance;
    }

    @Override
    public List<Book> getBookListByUid(List<Integer> uuidList) {
        List<Book> list = new ArrayList<>();
        for (Object o : bookDao.selectTableByUid(uuidList)) {
            list.add((Book) o);
        }

        return list;
    }

    @Override
    public List<Book> getBookByName(String bookName) {
        List<Book> list = new ArrayList<>();

        for(Object o: bookDao.selectTableByCol(new String[]{bookName}, "title")){
            list.add((Book) o);
        }
        return list;
    }

    @Override
    public void addBook(Grant grant, Book bookInfo) throws IOException {
        if (bookInfo.getUuid() == -1 && grant == Grant.Manager){
            bookDao.insertTable(Arrays.asList(new Book[] {bookInfo}));
        }
    }

    @Override
    public void deleteBook(Grant grant, Book book) throws IOException {
        if (grant == Grant.Manager) {
            book.setCheckOutState(CheckOutState.Deleted);
            bookDao.updateTable(book);
        }
    }

    @Override
    public void modifyBookInfo(Grant grant, Book bookInfo) throws IOException{
        if (grant == Grant.Manager) {
            bookDao.updateTable(bookInfo);
        }
    }

    @Override
    public List<String> getBookGenre() {
        return bookDao.getTablesGenre();
    }

    @Override
    public List<Book> getOnlySpecialGenre(String genre) {
        return bookDao.getGenresBook(genre);
    }

    @Override
    public void sortBook(String[] callName, int[] asc) {
        bookDao.sortTable(callName, asc);
    }

    @Override
    public List<Book> getBookList() {
        return bookDao.getBookList();
    }

    @Override
    public String getMaxValue(String colName) {
        return bookDao.getMaxValue(colName);
    }

    @Override
    public String getMinValue(String colName) {
        return bookDao.getMinValue(colName);
    }

    private Grant getMyGrant(Integer myUuid) {
        List<Object> memberList = memberDao.selectTableByUid(Arrays.asList(myUuid));
        if (!memberList.isEmpty()){
            Member myMember = (Member) memberList.get(0);
            return myMember.getGrant();
        }
        return Grant.None;
    }
    /*
    public String getMaxValue(String colName){
        bookDao.
    }
     */
}
