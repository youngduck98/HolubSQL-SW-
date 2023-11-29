package com.holub.application.model;

import com.holub.application.domain.member.Grant;

public class LoginToken {
    Integer userUid;
    Grant grant;

    public LoginToken(Integer userUid, Grant grant){
        this.userUid = userUid;
        this.grant = grant;
    }

    public Integer getUserUid() {
        return userUid;
    }

    public Grant getGrant() {
        return grant;
    }
}
