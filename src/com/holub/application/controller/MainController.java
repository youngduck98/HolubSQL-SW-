package com.holub.application.controller;

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
    private MainController(MainView MainView, int index, int size){
        this.mainView = MainView;
        this.index = index;
        this.size = size;
    }
    public static MainController getInstance(LoginToken token, MainView MainView, int index, int size){
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
            uid = bookRegisterView.getInteger("input Useruuid");
            List<Member> memberList = memberService.findMember(Arrays.asList(new Integer[]{uid}));
            if(memberList.isEmpty()){
                System.out.println("no member has " + uid);
                return;
            }
            //TODO: fixmemberInfo
        }
    }

    public void checkOut(){
        //checkOutMemberView
    }

    public void extendCheckout(){
        if(loginToken.getGrant() != Grant.Manager){
            System.out.println("권한이 존재하지 않습니다");
            return;
        }
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

    }

    public void searchBook(){
        String name = mainView.getBookName();
        bookList = bookService.getBookByName(name);
        if(bookList.isEmpty())
            mainView.print("no book like that");
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
        System.out.println(book.toList().toString());
        bookService.addBook(loginToken.getGrant(), book);
        bookList = bookService.getBookList();
    }

    public void fixBookInfo(){
        Integer uid = bookRegisterView.getInteger("input bookUid");
        List<Integer> input = new ArrayList<>();
        input.add(uid);
        bookList = bookService.getBookListByUid(input);
        if(bookList.isEmpty()){
            System.out.println("no book like that");
            fixMemberInfo();
        }
        //TODO: fixBookInfo
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
            index = Math.max(index, 0);
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
                case "0":
                    logout();
                    return;
            }
        }
    }
}
