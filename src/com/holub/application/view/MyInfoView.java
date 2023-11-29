package com.holub.application.view;

import com.holub.application.model.ctv.CTV;
import com.holub.application.model.vtc.MainManagerVTC;
import com.holub.application.model.vtc.MyInfoVTC;
import com.holub.application.model.vtc.VTC;
import com.holub.application.util.InputScanner;

public class MyInfoView {

    //TODO

    private final InputScanner scanner = InputScanner.getInstance();
    private final CTV ctv;

    public MyInfoView(CTV ctv) {
        this.ctv = ctv;
    }

    // 책 리스트 출력
    public void showMyInfo() {
        // TODO
        // CTV에서 내 정보를 찾아서 출력
    }

    @Override
    public VTC execute() {
        showMyInfo();
        // TODO MyInfoVTC에 내 로그인 uuid 넣어서 리턴
        return new MyInfoVTC();
    }

}
