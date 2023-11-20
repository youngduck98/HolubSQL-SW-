package com.holub.domain;

import com.holub.domain.member.Member;

public class LoginMember {

    private static Member member = new Member(-1);

    // Singleton
    private LoginMember(){}

    public Member getInstance(){
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

}
