package com.holub.application.service.member;

import com.holub.application.domain.member.Grant;
import com.holub.application.domain.member.Member;

import java.util.List;

public interface MemberService {

    // member 추가 -> 회원가입
    public void addMember(Member member);
    
    // uuid로 Member 한명 검색 -> 한명 찾기 & 내 정보 찾기
    public Member findOneMember(Integer uuid);

    public void sortMember(String[] colName, int[] asc);
    
    // uuid 리스트를 사용해서 Member 리스트 반환 -> 모두 찾기
    public List<Member> findMember(List<Integer> uuids);

    // uuid와 수정된 memberInfo를 받아서 member 정보 수정
    public void fixMemberInfo(Member member);

}
