package com.holub.application.service.login;

import com.holub.application.dao.Dao;
import com.holub.application.dao.MemberDao;
import com.holub.application.domain.member.Grant;
import com.holub.application.domain.member.Member;
import com.holub.application.model.Model;

import java.util.Arrays;
import java.util.List;

public class LoginServiceImpl implements LoginService{

    private static LoginService instance;
    private final MemberDao memberDao;

    private LoginServiceImpl(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    public static LoginService getInstance(MemberDao memberDao){
        if (instance == null) {
            instance = new LoginServiceImpl(memberDao);
        }
        return instance;
    }

    @Override
    public void login(Model model) {
        Grant grant = getMyGrant(model);

        if (grant == Grant.None) {
            String id = (String) model.getAttribute("id");
            String password = (String) model.getAttribute("password");

            Object temp = memberDao.findByIdAndPassword(id, password);

            model.clearAttribute();
            if (temp != null) {
                Member member = (Member) temp;
                model.addAttribute("myInfo", (Integer) (member.getUuid()));
            }
        }
    }

    @Override
    public void logout(Model model) {
        model.removeAttribute("myInfo");
        model.clearAttribute();
    }

    private Grant getMyGrant(Model model) {

        if (!model.containsAttribute("myInfo"))
            return Grant.None;

        Integer myUuid = (Integer) model.getAttribute("myInfo");

        List<Object> memberList = memberDao.selectTable(
                Arrays.asList(new Integer[] {myUuid}), null, null);
        if (!memberList.isEmpty()){
            Member myMember = (Member) memberList.get(0);
            return myMember.getGrant();
        }

        return Grant.None;
    }

}
