package com.holub.application.service.checkout;

import com.holub.application.dao.Dao;
import com.holub.application.domain.checkout.CheckOut;
import com.holub.application.domain.member.Grant;

import java.time.LocalDate;
import java.util.List;

public class CheckOutServiceImpl implements CheckOutService{

    private static CheckOutService instance;
    private final Dao dao;

    private CheckOutServiceImpl(Dao dao){
        this.dao = dao;
    }

    public static CheckOutService getInstance(Dao dao){
        if (instance == null) {
            instance = new CheckOutServiceImpl(dao);
        }
        return instance;
    }

    @Override
    public void checkOutBook(Grant grant, Integer uuid) {
        // TODO
    }

    @Override
    public void returnBook(Grant grant, Integer uuid) {
        // TODO
    }

    @Override
    public void extensionDueDate(Grant grant, Integer uuid, LocalDate date) {
        // TODO
    }

    @Override
    public List<CheckOut> searchCheckOut(Grant grant, List<Integer> uuids) {
        // TODO

        return null;
    }
}
