package com.holub.application.controller;

import com.holub.application.Config;
import com.holub.application.domain.book.Book;
import com.holub.application.domain.checkout.CheckOut;
import com.holub.application.domain.member.Grant;
import com.holub.application.domain.member.Member;
import com.holub.application.model.LoginToken;
import com.holub.application.model.vtc.CheckOutManagerVTC;
import com.holub.application.service.book.BookService;
import com.holub.application.service.book.BookServiceImpl;
import com.holub.application.service.checkout.CheckOutService;
import com.holub.application.service.checkout.CheckOutServiceImpl;
import com.holub.application.service.member.MemberService;
import com.holub.application.service.member.MemberServiceImpl;
import com.holub.application.view.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainController {

    Config config = Config.getInstance();

    private static MainController mainController;
    private int index;
    private int size;

    private static LoginToken loginToken;
    public BookService bookService = config.getBookService();
    public MemberService memberService = config.getMemberService();
    public CheckOutService checkOutService = config.getCheckOutService();
    private MainView mainView;
    private BookRegisterView bookRegisterView = new BookRegisterView();

    private CheckOutManagerView checkOutManagerView = new CheckOutManagerView();
    private CheckOutMemberView checkOutMemberView = new CheckOutMemberView();

    List<Book> bookList;
    private MainController(MainView MainView, int index, int size) throws IOException {
        this.mainView = MainView;
        this.index = index;
        this.size = size;
    }
    public static MainController getInstance(LoginToken token, MainView MainView, int index, int size) throws IOException {
        if(mainController == null)
            mainController = new MainController(MainView, index, size);
        loginToken = token;
        return mainController;
    }

    public static MainController getInstance(){
        if(mainController == null)
            throw new NullPointerException();
        return mainController;
    }

    public void logout(){
        return;
    }

    public void myInfo(){
        List<Member> memberList = memberService.findMember(
                Arrays.asList(new Integer[]{loginToken.getUserUid()}));
        if(memberList.isEmpty())
            throw new IllegalArgumentException("how you login?");
        MyInfoView.showMyInfo(memberList.get(0));
    }

    public void fixMemberInfo(){
        Integer uid = null;
        if(loginToken.getGrant() == Grant.Member){
            uid = loginToken.getUserUid();
        }
        if(loginToken.getGrant() == Grant.Manager){
            mainView.print("userUid: " + memberService.findAllUid().toString());
            uid = bookRegisterView.getInteger("input Useruuid");
            List<Member> memberList = memberService.findMember(Arrays.asList(new Integer[]{uid}));
            if(memberList.isEmpty()){
                System.out.println("no member has " + uid);
                return;
            }

            Member updateInfoMember = ((MainManagerView)mainView).viewOfUpdateMember(memberList.get(0));
            mainView.print(updateInfoMember.toList().toString());

            memberService.fixMemberInfo(updateInfoMember);
        }
    }

    public void checkOut(){
        //책 선택 -> 대출
        Integer bookUid = checkOutMemberView.getInteger("bookUid");
        List<Book> book = bookService.
                getBookListByUid(Arrays.asList(new Integer[]{bookUid}));
        if(book.isEmpty()){
            checkOutMemberView.println("no bookUid like that" + bookUid);
            return;
        }
        try {
            CheckOut checkOut = checkOutMemberView.checkOutProcess(bookUid, loginToken);
            checkOutService.checkOutBook(loginToken.getGrant(), checkOut);
        }
        catch (Exception e){
            System.out.println("error at checkoutBook" + e);
        }
    }

    public void extendCheckout() throws IOException {
        if(loginToken.getGrant() != Grant.Manager){
            System.out.println("권한이 존재하지 않습니다");
            return;
        }
        Integer uid = mainView.getIntegerInList(memberService.findAllUid());
        List<CheckOut> list = checkOutService.getCheckOutList(loginToken.getGrant(), Arrays.asList(new Integer[]{uid}));
        checkOutManagerView.showCheckOutList(list);
        CheckOutManagerVTC cmv = checkOutManagerView.executeSelectedMenu();
        try {
            checkOutService.extensionDueDate(loginToken.getGrant(), cmv.getUpdateDueDate(), cmv.getUpdateCheckOutUuid());
        } catch (Exception e) {
            System.out.println("their no checkout uid like" + cmv.getUpdateCheckOutUuid());
            return;
        }
    }

    //반납
    public void returnBook(){
        //본인 체크아웃 확인
        List<CheckOut> list = checkOutService.getMyCheckOutInfo(loginToken.getUserUid());
        System.out.println(list.size());
        checkOutMemberView.showMyCheckOutList(list);
        //체크아웃 할 uid 받기
        List<Integer> uids = new ArrayList<>();
        for(CheckOut co : list){
            uids.add(co.getUuid());
        }
        Integer uid = checkOutMemberView.getIntegerInList(uids);
        if(uid == null) {
            System.out.println("no uid like that");
            return;
        }
        checkOutService.returnBook(loginToken.getGrant(), uid);
    }

    public void searchBook(){
        String name = mainView.getBookName();
        bookList = bookService.getBookByName(name);
        if(bookList.isEmpty())
            mainView.print("no book like that");
        mainView.print(bookList.get(0).toList().toString());
    }

    public void sortBook(){
        try {
            String colName = mainView.getColName();
            int asc = mainView.getAsc();
            bookService.sortBook(new String[]{colName}, new int[]{asc});
            bookList = bookService.getBookList();
        } catch (Exception e) {
            System.out.println("failed to get sort data" + e);
        }
    }

    public void registerBook() throws IOException {
        Book book = bookRegisterView.execute();
        System.out.println(book.toList());
        bookService.addBook(loginToken.getGrant(), book);
        bookList = bookService.getBookList();
    }

    public void fixBookInfo(){
        Integer uid = bookRegisterView.getInteger("input bookUid");
        List<Integer> input = new ArrayList<>();
        input.add(uid);

        List<Book> books= bookService.getBookListByUid(input);
        if(books.isEmpty()){
            System.out.println("no book like that");
            fixBookInfo();
        }

        try {
            mainView.showBookList(books.subList(0, 1));
            BookUpdateView bookUpdateView = new BookUpdateView(books.get(0));
            Book bookInfo = bookUpdateView.execute();
            bookService.modifyBookInfo(loginToken.getGrant(), bookInfo);
            bookList = bookService.getBookList();
        }
        catch (Exception e){
            System.out.println("error at modify Book info " + e);
        }
    }


    public void max() {
        try {
            String colName = mainView.getColName();
            String max = bookService.getMaxValue(colName);
            mainView.print("max_value: " + max);
            bookList = bookService.getBookList();
        } catch (Exception e) {
            System.out.println("failed to get max value of data" + e);
        }
    }

    public void min() {
        try {
            String colName = mainView.getColName();
            String min = bookService.getMinValue(colName);
            mainView.print("min_value: " + min);
            bookList = bookService.getBookList();
        } catch (Exception e) {
            System.out.println("failed to get min value of data" + e);
        }
    }

    public void genre(){
        try {
            System.out.println(bookService.getBookGenre());
        }
        catch (Exception e){
            System.out.println("failed to extract library's Genre");
        }
    }

    public void analyze(){
        String str = mainView.getString("1: max, 2: min, 3: genre");
        switch (str){
            case "1":max();break;
            case "2":min();break;
            case "3":genre();
        }
    }

    public void beforePage(){
        index = Math.max(index-size, 0);
    }

    public void nextPage(){
        index = Math.min(index+ size, bookList.size());
    }

    public void execute() throws IOException {
        /*
        * System.out.println("(0)로그 아웃 (1)내 정보 (2)회원 정보/수정 (3)대출 정보/연장");
        System.out.println("(4)책 검색 (5)책 정렬 (6)책 등록 (7)책 수정");
        System.out.println("(Q)이전 페이지 (W)다음 페이지 ");
        * */
        bookList = bookService.getBookList();
        while(true) {
            /**
             * System.out.println("(0)로그 아웃 (1)내 정보 (3)대출 정보/반납 (4)책 검색 (5)책 정렬 (6)책 대출");
             *         System.out.println("(Q)이전 페이지 (W)다음 페이지 ");
             */
            mainView.showMenu();
            index = Math.max(Math.min(index, bookList.size() - size), 0);
            int caledSize = Math.min(index + size, bookList.size()) - index;
            caledSize = Math.max(caledSize, 0);
            mainView.showBookList(bookList.subList(index, index + caledSize));
            String MenuNum = mainView.getMenu();
            switch (MenuNum) {
                case "1":
                    myInfo();
                    break;
                case "2":
                    fixMemberInfo();
                    break;
                case "3":
                    if(loginToken.getGrant() == Grant.Manager)
                        extendCheckout();
                    if(loginToken.getGrant() == Grant.Member)
                        returnBook();
                    break;
                case "4":
                    searchBook();
                    break;
                case "5":
                    sortBook();
                    break;
                case "q":
                case "Q":
                    beforePage();
                    break;
                case "w":
                case "W":
                    nextPage();
                    break;
                case "6":
                    if(loginToken.getGrant() == Grant.Manager)
                        registerBook();
                    if(loginToken.getGrant() == Grant.Member)
                        checkOut();
                    break;
                case "7":
                    fixBookInfo();
                    break;
                case "8":
                    analyze();
                    break;
                case "0":
                    logout();
                    return;
            }
        }
    }
}
