package com.holub.application.service.member;

import com.holub.application.dao.Dao;
import com.holub.application.dao.MemberDao;
import com.holub.application.domain.member.Grant;
import com.holub.application.domain.member.Member;
import com.holub.application.model.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemberServiceImpl implements MemberService{

    private static MemberService instance;
    private final MemberDao memberDao;

    private MemberServiceImpl(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    public static MemberService getInstance(MemberDao memberDao){
        if (instance == null) {
            instance = new MemberServiceImpl(memberDao);
        }
        return instance;
    }

    @Override
    public void addMember(Member member) {
        memberDao.insertTable(member.toList());
    }

    private List<Member> makeMemberListFromObjectList(List<Object> list){
        List<Member> ret = new ArrayList<>();
        for(Object o: list){
            ret.add((Member) o);
        }
        return ret;
    }

    @Override
    public Member findOneMember(Integer uuid, String[] colName, int[] asc) {
        // TODO
        List<Object> ret = memberDao.selectTable(Arrays.asList(new Integer[]{uuid}), colName, asc);
        if(ret == null)
            throw  new NullPointerException();
        if(ret.isEmpty())
            throw new IllegalArgumentException();
        model.clearAttribute();
        return (Member) ret.get(0);
    }

    @Override
    public List<Member> findMember(List<Integer> uuids, String[] colName, int[] asc) {
        List<Object> output = memberDao.selectTable(uuids, colName, asc);
        if(output == null)
            throw new NullPointerException();
        //들어온 uuid list의 길이와 나온 Member list의 길이가 다르면 illegalargumentException
        //없는 uuid를 포함하였다는 의미이므로
        if(uuids.size() != output.size())
            throw new IllegalArgumentException();
        return makeMemberListFromObjectList(output);
    }

    @Override
    public void fixMemberInfo(Member member) {
        try {
            memberDao.updateTable(member);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // TODO
    }
}
