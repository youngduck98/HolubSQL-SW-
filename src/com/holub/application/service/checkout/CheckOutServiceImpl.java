package com.holub.application.service.checkout;

import com.holub.application.dao.CheckOutDao;
import com.holub.application.dao.Dao;
import com.holub.application.dao.MemberDao;
import com.holub.application.domain.book.Book;
import com.holub.application.domain.checkout.CheckOut;
import com.holub.application.domain.member.Grant;
import com.holub.application.domain.member.Member;
import com.holub.application.model.Model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CheckOutServiceImpl implements CheckOutService{

    private static CheckOutService instance;
    private final CheckOutDao checkOutDao;
    private final MemberDao memberDao;

    private CheckOutServiceImpl(CheckOutDao checkOutDao, MemberDao memberDao){
        this.checkOutDao = checkOutDao;
        this.memberDao = memberDao;
    }

    public static CheckOutService getInstance(CheckOutDao checkOutDao, MemberDao memberDao){
        if (instance == null) {
            instance = new CheckOutServiceImpl(checkOutDao, memberDao);
        }
        return instance;
    }

    @Override
    public void checkOutBook(Model model) {
        Grant grant = getMyGrant(model);
        CheckOut checkOutInfo = (CheckOut) model.getAttribute("addCheckOutInfo");

        if (checkOutInfo.getUuid() == -1 && grant == Grant.Member) {
            checkOutDao.insertTable(Arrays.asList(new CheckOut[] {checkOutInfo}));
        }

        model.clearAttribute();
    }

    @Override
    public void returnBook(Model model) {
        Grant grant = getMyGrant(model);
        Integer returnUuid = (Integer) model.getAttribute("returnUuid");

        // TODO : userUUID를 사용해서 그 유저만 빌린 대출정보를 리턴하는 기능이 필요


    }

    @Override
    public void extensionDueDate(Model model) {
        Grant grant = getMyGrant(model);
        LocalDate dueDateInfo = (LocalDate) model.getAttribute("extensionDueDateInfo");
        Integer dueDateUuid = (Integer) model.getAttribute("extensionDueDateUuid");
        CheckOut check =

        // TODO : dueDateUuid를 사용해서 CheckOut객체를 select한 뒤, extensionDueDateInfo 로 update


        if ()
    }

    @Override
    public List<CheckOut> searchCheckOut(Model model) {
        // TODO

        return null;
    }

    private Grant getMyGrant(Model model) {
        Integer myUuid = (Integer) model.getAttribute("myInfo");
        Member myMember = (Member) memberDao.selectTable(
                Arrays.asList(new Integer[] {myUuid}), null, null).get(0);
        return myMember.getGrant();
    }

}
