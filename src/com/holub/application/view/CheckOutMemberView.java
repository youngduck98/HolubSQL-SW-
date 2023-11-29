package com.holub.application.view;

import com.holub.application.domain.checkout.CheckOut;
import com.holub.application.model.vtc.CheckOutMemberVTC;
import com.holub.application.model.vtc.VTC;
import com.holub.application.util.InputScanner;

import java.util.List;

public class CheckOutMemberView {

    private final InputScanner scanner = InputScanner.getInstance();

    // 책 리스트 출력
    public void showMyCheckOutList(List<CheckOut> checkOutList) {
        for(CheckOut checkOut: checkOutList){

        }

    }

    // String 입력 받기
    public String getString() {
        System.out.print(">> ");
        return scanner.inputString();
    }

    public Integer getInteger() {
        System.out.println(">> ");
        return scanner.inputInteger();
    }

    public VTC executeSelectedMenu(String menu) {
        // TODO view를 새로 만들어도 될 것 같은데
        if (menu.equals("2")){
            System.out.println("반납할 대여 테이블의 Index를 입력해 주세요");
            Integer returnBookUuid = getInteger();
            return new CheckOutMemberVTC(menu, returnBookUuid);
        }
        return new CheckOutMemberVTC(menu);
    }

    public VTC execute() {
        while(true) {
            //showMyCheckOutList();
            String selectedMenu = getString();
            if (isSelectedMenuValid(selectedMenu))
                // TODO myUuid 넣어서 리턴해 줘야함
                return executeSelectedMenu(selectedMenu);
            else
                System.out.println("<<ERROR>> 메뉴를 다시 입력해 주세요");
        }
    }

    public boolean isSelectedMenuValid(String selectedMenu) {
        switch(selectedMenu){
            case "1": case "2":
                return true;
            default:
                return false;
        }
    }

}
