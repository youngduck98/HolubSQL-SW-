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

    public BookService getBookService() throws IOException {
        return BookServiceImpl.getInstance(BookDao.getInstance(
                "",
                ""
        ));
    }

    public CheckOutService getCheckOutService() throws IOException {
        return CheckOutServiceImpl.getInstance(CheckOutDao.getInstance(
                "",
                ""
        ));
    }

    public LoginService getLoginService() throws IOException {
        return LoginServiceImpl.getInstance(MemberDao.getInstance(
                "",
                ""
        ));
    }

    public MemberService getMemberService() throws IOException {
        return MemberServiceImpl.getInstance(MemberDao.getInstance(
                "",
                ""
        ));
    }

}
