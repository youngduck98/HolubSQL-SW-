package com.holub.application.view;

import com.holub.application.model.ctv.CTV;
import com.holub.application.model.vtc.CheckOutManagerVTC;
import com.holub.application.model.vtc.CheckOutMemberVTC;
import com.holub.application.model.vtc.VTC;
import com.holub.application.util.InputScanner;

import java.time.LocalDate;

public class CheckOutManagerView implements View{

    private final InputScanner scanner = InputScanner.getInstance();
    private final CTV ctv;

    public CheckOutManagerView(CTV ctv) {
        this.ctv = ctv;
    }

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

    public VTC executeSelectedMenu(String menu) {
        // TODO validation 필요
        // TODO view를 새로 만들어도 될 것 같은데
        if (menu.equals("2")){
            System.out.println("연장할 대여 테이블의 Index를 입력해 주세요");
            Integer updateCheckOutUuid = getInteger();
            System.out.println("수정할 기한을 입력합니다");
            Integer year = getInteger("year");
            Integer month = getInteger("month");
            Integer day = getInteger("day");
            LocalDate updatedDueDate = LocalDate.of(year, month, day);
            return new CheckOutManagerVTC(menu, updateCheckOutUuid, updatedDueDate);
        }
        return new CheckOutManagerVTC(menu);
    }

    @Override
    public VTC execute() {
        while(true) {
            showMenu();
            showCheckOutList();
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
            case "1": case "2": case"Q":
            case "q": case "W": case "w":
                return true;
            default:
                return false;
        }
    }

}
