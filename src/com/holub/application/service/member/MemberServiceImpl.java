package com.holub.application.service.member;

import com.holub.application.dao.Dao;
import com.holub.application.domain.member.Grant;
import com.holub.application.domain.member.Member;

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
    public void addMember(Grant grant, Member memberInfo) {
        // TODO
    }

    @Override
    public Member findMember(Grant grant, Integer uuid) {
        // TODO
        return null;
    }

    @Override
    public List<Member> findMember(Grant grant, List<Integer> uuids) {
        // TODO
        return null;
    }

    @Override
    public void fixMemberInfo(Grant grant, Integer uuid, Member memberInfo) {
        // TODO
    }
}
