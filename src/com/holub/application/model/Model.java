package com.holub.application.model;

import com.holub.application.domain.member.Grant;
import com.holub.application.model.ctv.CTV;
import com.holub.application.model.vtc.VTC;

import java.util.HashMap;
import java.util.Map;

public class Model {
    CTV state;
    VTC Input;
    Grant grant;
    Integer userUuid;

    public Integer getIndex() {
        return index;
    }

    public Integer getSize() {
        return size;
    }

    Integer index;
    Integer size;

    public CTV getState() {
        return state;
    }

    public VTC getInput() {
        return Input;
    }

    public Grant getGrant() {
        return grant;
    }

    public Integer getUserUuid() {
        return userUuid;
    }
}
