package com.holub.application.service.member;

import com.holub.application.domain.member.Grant;
import com.holub.application.domain.member.Member;
import com.holub.application.model.Model;

import java.util.List;

public interface MemberService {

    // member 추가 -> 회원가입
    public void addMember(Model model);
    
    // uuid로 Member 한명 검색 -> 한명 찾기 & 내 정보 찾기
    public Member findOneMember(Model model);
    
    // uuid 리스트를 사용해서 Member 리스트 반환 -> 모두 찾기
    public List<Member> findMember(Model model);

    // uuid와 수정된 memberInfo를 받아서 member 정보 수정
    public void fixMemberInfo(Model model);

}
