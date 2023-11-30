package com.holub.application.view;

import com.holub.application.domain.checkout.CheckOut;
import com.holub.application.model.LoginToken;
import com.holub.application.model.vtc.CheckOutMemberVTC;
import com.holub.application.model.vtc.VTC;
import com.holub.application.util.InputScanner;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public class CheckOutMemberView {

    private final InputScanner scanner = InputScanner.getInstance();

    // 책 리스트 출력
    public void showMyCheckOutList(List<CheckOut> checkOutList) {
        for(CheckOut checkOut: checkOutList){
            printCheckout(checkOut);
        }
    }

    public void printCheckout(CheckOut checkout){
        for(Object o1: checkout.toList()){
            System.out.print(o1 + " ");
        }
        System.out.println();
    }

    // String 입력 받기
    public String getString(String str) {
        System.out.print(str + ">> ");
        return scanner.inputString();
    }

    public Integer getInteger(String str) {
        System.out.println(str + ">> ");
        return scanner.inputInteger();
    }

    public Integer getIntegerInList(List<Integer> uids){
        int n = getInteger("input checkout uid");
        if(uids.contains(n))
            return n;
        return null;
    }

    public VTC executeSelectedMenu(String menu) {
        System.out.println("반납할 대여 테이블의 Index를 입력해 주세요");
        Integer returnBookUuid = getInteger("반납할 대여 uuid");
        return new CheckOutMemberVTC(menu, returnBookUuid);
    }

    public CheckOut checkOutProcess(Integer bookUid, LoginToken token){
        return new CheckOut(
                token.getUserUid(),
                bookUid, LocalDate.now(),
                LocalDate.now().plusWeeks(1)
        );
    }

    public void println(String str){
        System.out.println(str);
    }
}
