package com.holub.application.model;

import com.holub.application.model.ctv.CTV;
import com.holub.application.model.vtc.VTC;

import java.util.HashMap;
import java.util.Map;

public class Model {
    CTV state;
    VTC Input;
    Object myInfo;

    public CTV getState() {
        return state;
    }

    public VTC getInput() {
        return Input;
    }

    public Object getMyInfo() {
        return myInfo;
    }
}
