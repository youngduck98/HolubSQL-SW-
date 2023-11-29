package com.holub.application;

import com.holub.application.dao.BookDao;
import com.holub.application.dao.CheckOutDao;
import com.holub.application.dao.MemberDao;
import com.holub.application.domain.member.Member;
import com.holub.application.service.book.BookService;
import com.holub.application.service.book.BookServiceImpl;
import com.holub.application.service.checkout.CheckOutService;
import com.holub.application.service.checkout.CheckOutServiceImpl;
import com.holub.application.service.login.LoginService;
import com.holub.application.service.login.LoginServiceImpl;
import com.holub.application.service.member.MemberService;
import com.holub.application.service.member.MemberServiceImpl;

import java.io.IOException;

public class Config {
    static String baseUrl = "C:/dp2023";
    public BookService getBookService() throws IOException {
        return BookServiceImpl.getInstance(
                BookDao.getInstance(baseUrl, "Book"),
                MemberDao.getInstance(baseUrl, "Member")
        );
    }

    public CheckOutService getCheckOutService() throws IOException {
        return CheckOutServiceImpl.getInstance(
                CheckOutDao.getInstance(baseUrl, "CheckOut"),
                MemberDao.getInstance(baseUrl, "Member")
        );
    }

    public LoginService getLoginService() throws IOException {
        return LoginServiceImpl.getInstance();
    }

    public MemberService getMemberService() throws IOException {
        return MemberServiceImpl.getInstance(
                MemberDao.getInstance(baseUrl, "Member")
        );
    }

    public void execute() throws Exception{
        getBookService();
        getCheckOutService();
        getLoginService();
        getMemberService();
    }
}
