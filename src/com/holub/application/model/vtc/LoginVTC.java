package com.holub.application.model.vtc;

import com.holub.application.domain.member.Grant;

public class LoginVTC implements VTC{

    private final String id;
    private final String password;
    private final Grant grant;

    public LoginVTC(String id, String password, Grant grant) {
        this.id = id;
        this.password = password;
        this.grant = grant;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Grant getGrant(){return grant;}
}
