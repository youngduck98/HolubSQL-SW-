package com.holub.application.view;

import com.holub.application.model.vtc.CheckOutManagerVTC;
import com.holub.application.model.vtc.VTC;
import com.holub.application.util.InputScanner;

import java.time.LocalDate;

public class CheckOutManagerView{

    private final InputScanner scanner = InputScanner.getInstance();

    // 메뉴 출력
    public void showMenu() {
        System.out.println("(1)메인 화면으로 이동 (2)수정(연장)");
        System.out.println("(Q)이전 페이지 (W)다음 페이지");
    }

    // 책 리스트 출력
    public void showCheckOutList() {
        // TODO

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

    public Integer getInteger(String str) {
        System.out.println(str + " >> ");
        return scanner.inputInteger();
    }

    public CheckOutManagerVTC executeSelectedMenu() {
        try {
            System.out.println("연장할 대여 테이블의 Index를 입력해 주세요");
            Integer updateCheckOutUuid = getInteger();
            System.out.println("수정할 기한을 입력합니다");
            Integer year = getInteger("year");
            Integer month = getInteger("month");
            Integer day = getInteger("day");
            LocalDate updatedDueDate = LocalDate.of(year, month, day);
            return new CheckOutManagerVTC(updateCheckOutUuid, updatedDueDate);
        }
        catch (Exception e){
            System.out.println(e);
            return executeSelectedMenu();
        }
    }
}
