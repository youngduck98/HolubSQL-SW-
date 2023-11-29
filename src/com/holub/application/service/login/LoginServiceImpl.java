package com.holub.application.service.login;

import com.holub.application.dao.Dao;
import com.holub.application.dao.MemberDao;
import com.holub.application.domain.member.Grant;
import com.holub.application.domain.member.Member;
import com.holub.application.model.vtc.LoginVTC;

import java.util.Arrays;
import java.util.List;

public class LoginServiceImpl implements LoginService{

    private static LoginService instance;
    private final MemberDao memberDao;

    private LoginServiceImpl(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    public static LoginService getInstance(){
        if (instance == null) {
            instance = new LoginServiceImpl(MemberDao.getInstance());
        }
        return instance;
    }

    @Override
    public Integer login(LoginVTC vtc) {

        if (vtc.getGrant() == Grant.None) {

            Object temp = memberDao.findByIdAndPassword(vtc.getId(), vtc.getPassword());

            if (temp != null) {
                Member member = (Member) temp;
                return member.getUuid();
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void logout(Integer myUuid) {
        //TODO
    }

    private Grant getMyGrant(Integer myUuid) {
        if(myUuid == null)
            throw new NullPointerException();

        List<Object> memberList = memberDao.selectTableByUid(Arrays.asList(new Integer[] {myUuid}));
        if (!memberList.isEmpty()){
            Member myMember = (Member) memberList.get(0);
            return myMember.getGrant();
        }

        return Grant.None;
    }

}
