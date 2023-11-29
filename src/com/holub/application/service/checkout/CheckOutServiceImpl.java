package com.holub.application.service.checkout;

import com.holub.application.dao.CheckOutDao;
import com.holub.application.dao.MemberDao;
import com.holub.application.domain.checkout.CheckOut;
import com.holub.application.domain.member.Grant;
import com.holub.application.domain.member.Member;
import com.holub.application.model.Model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public void checkOutBook(Grant grant, CheckOut checkOutInfo) {
        if (checkOutInfo.getUuid() == -1 && grant == Grant.Member) {
            checkOutDao.insertTable(Arrays.asList(new CheckOut[] {checkOutInfo}));
        }
    }

    @Override
    public void returnBook(Grant grant, Integer returnUuid) {
        List<Object> checkOutList = checkOutDao.selectTable(
                Arrays.asList(new Integer[] {returnUuid}), null, null
        );

        if (!checkOutList.isEmpty() && grant == Grant.Member) {
            checkOutDao.deleteRow(returnUuid);
        }
    }

    @Override
    public void extensionDueDate(Grant grant, LocalDate dueDateInfo, Integer dueDateUuid) throws IOException{
        List<Object> checkOutList = checkOutDao.selectTable(
                Arrays.asList(new Integer[] {dueDateUuid}), null, null
        );

        if (!checkOutList.isEmpty() && grant == Grant.Manager) {
            CheckOut checkOut = (CheckOut) checkOutList.get(0);
            checkOut.setDueDate(dueDateInfo);
            checkOutDao.updateTable(checkOut);
        }
    }

    @Override
    public List<CheckOut> getCheckOutList(Grant grant, List<Integer> uuidList, String[] callName, int[] asc) {
        if (grant == Grant.Manager) {
            List<CheckOut> list = new ArrayList<>();

            List<Object> checkOutList = checkOutDao.selectTable(uuidList, callName, asc);
            if (!checkOutList.isEmpty()) {
                for (Object o : checkOutDao.selectTable(uuidList, callName, asc)) {
                    list.add((CheckOut) o);
                }
            }
            return list;
        }
        return null;
    }

    @Override
    public List<CheckOut> getMyCheckOutInfo(Integer myUuid) {
        List<Object> checkOutList = checkOutDao.findCheckoutListFromUser(myUuid);

        if (!checkOutList.isEmpty())
            return checkOutList.stream()
                    .map(c -> (CheckOut) c)
                    .collect(Collectors.toList());
        else
            return null;
    }

    private Grant getMyGrant(Integer myUuid ) {
        List<Object> memberList = memberDao.selectTable(
                Arrays.asList(new Integer[] {myUuid}), null, null);
        if (!memberList.isEmpty()){
            Member myMember = (Member) memberList.get(0);
            return myMember.getGrant();
        }
        return Grant.None;
    }

}
