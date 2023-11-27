package com.holub.application.service.checkout;

import com.holub.application.dao.CheckOutDao;
import com.holub.application.dao.Dao;
import com.holub.application.domain.checkout.CheckOut;
import com.holub.application.domain.member.Grant;
import com.holub.application.model.Model;

import java.time.LocalDate;
import java.util.List;

public class CheckOutServiceImpl implements CheckOutService{

    private static CheckOutService instance;
    private final CheckOutDao checkOutDao;

    private CheckOutServiceImpl(CheckOutDao checkOutDao){
        this.checkOutDao = checkOutDao;
    }

    public static CheckOutService getInstance(CheckOutDao checkOutDao){
        if (instance == null) {
            instance = new CheckOutServiceImpl(checkOutDao);
        }
        return instance;
    }

    @Override
    public void checkOutBook(Model model) {
        // TODO
        if (grant == Grant.Member) {
            CheckOut checkOut = new CheckOut();
            dao.insertTable(List<Object> domainList);
        }
    }

    @Override
    public void returnBook(Model model) {
        // TODO
    }

    @Override
    public void extensionDueDate(Model model) {
        // TODO
    }

    @Override
    public List<CheckOut> searchCheckOut(Model model) {
        // TODO

        return null;
    }
}
