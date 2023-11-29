package com.holub.application.model.vtc;

import com.holub.application.domain.member.Grant;

public class MyInfoVTC implements VTC{

    private final Integer myUuid;

    public MyInfoVTC(Integer myUuid) {
        this.myUuid = myUuid;
    }

    public Integer getGrant() {
        return myUuid;
    }
}
