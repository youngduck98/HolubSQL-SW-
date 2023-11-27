package com.holub.application.service.member;

import com.holub.application.dao.Dao;
import com.holub.application.domain.member.Grant;
import com.holub.application.domain.member.Member;
import com.holub.application.model.Model;

import java.util.List;

public class MemberServiceImpl implements MemberService{

    private static MemberService instance;
    private final Dao dao;

    private MemberServiceImpl(Dao dao){
        this.dao = dao;
    }

    public static MemberService getInstance(Dao dao){
        if (instance == null) {
            instance = new MemberServiceImpl(dao);
        }
        return instance;
    }

    @Override
    public void addMember(Model model) {
        // TODO
    }

    @Override
    public Member findMember(Model model) {
        // TODO
        return null;
    }

    @Override
    public List<Member> findMember(Model model) {
        // TODO
        return null;
    }

    @Override
    public void fixMemberInfo(Model model) {
        // TODO
    }
}
