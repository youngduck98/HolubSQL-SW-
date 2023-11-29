package com.holub.application.model.vtc;

import com.holub.application.domain.book.CheckOutState;
import com.holub.application.domain.book.Location;
import com.holub.application.domain.member.Member;

import java.time.LocalDate;

public class SignUpVTC implements VTC{

    private final Member member;

    public SignUpVTC(Member member){
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
}
