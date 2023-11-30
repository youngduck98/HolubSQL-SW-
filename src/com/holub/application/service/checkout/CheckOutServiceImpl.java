package com.holub.application.service.checkout;

import com.holub.application.dao.CheckOutDao;
import com.holub.application.dao.MemberDao;
import com.holub.application.domain.checkout.CheckOut;
import com.holub.application.domain.member.Grant;
import com.holub.application.domain.member.Member;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CheckOutServiceImpl implements CheckOutService{

    private static CheckOutService instance;
    private CheckOutDao checkOutDao = CheckOutDao.getInstance();
    private MemberDao memberDao = MemberDao.getInstance();

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

    public static CheckOutService getInstance(){
        if(instance == null)
            throw  new NullPointerException();
        return instance;
    }

    @Override
    public void checkOutBook(Grant grant, CheckOut checkOutInfo) throws IOException {
        if (checkOutInfo.getUuid() == -1 && grant == Grant.Member) {
            checkOutDao.insertTable(Arrays.asList(new CheckOut[] {checkOutInfo}));
        }
    }

    @Override
    public void returnBook(Grant grant, Integer returnUuid) {
        List<Object> checkOutList = checkOutDao.selectTableByUid(
                Arrays.asList(new Integer[] {returnUuid})
        );

        if (!checkOutList.isEmpty() && grant == Grant.Member) {
            checkOutDao.deleteRow(returnUuid);
        }
    }

    @Override
    public void extensionDueDate(Grant grant, LocalDate dueDateInfo, Integer dueDateUuid) throws IOException{
        List<Object> checkOutList = checkOutDao.selectTableByUid(
                Arrays.asList(new Integer[] {dueDateUuid})
        );

        if (!checkOutList.isEmpty() && grant == Grant.Manager) {
            CheckOut checkOut = (CheckOut) checkOutList.get(0);
            checkOut.setDueDate(dueDateInfo);
            checkOutDao.updateTable(checkOut);
        }
    }

    @Override
    public List<CheckOut> getCheckOutList(Grant grant, List<Integer> uuidList) {
        if (grant == Grant.Manager) {
            List<CheckOut> list = new ArrayList<>();

            List<Object> checkOutList = checkOutDao.selectTableByUid(uuidList);
            if (!checkOutList.isEmpty()) {
                for (Object o : checkOutDao.selectTableByUid(uuidList)) {
                    list.add((CheckOut) o);
                }
            }
            return list;
        }
        return null;
    }

    @Override
    public void sortCheckOut(String[] callName, int[] asc) {
        checkOutDao.sortTable(callName, asc);
    }

    @Override
    public List<CheckOut> getMyCheckOutInfo(Integer myUuid) {
        List<Object> checkOutList = checkOutDao.findCheckoutListFromUser(myUuid);
        List<CheckOut> ret = new ArrayList<>();
        for(Object o1: checkOutList){
            ret.add((CheckOut) o1);
        }
        return ret;
    }

    private Grant getMyGrant(Integer myUuid) {
        List<Object> memberList = memberDao.selectTableByUid(
                Arrays.asList(new Integer[] {myUuid}));
        if (!memberList.isEmpty()){
            Member myMember = (Member) memberList.get(0);
            return myMember.getGrant();
        }
        return Grant.None;
    }

}
