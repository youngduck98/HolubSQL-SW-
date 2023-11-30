package com.holub.application.view;

import com.holub.application.domain.book.Book;
import com.holub.application.domain.member.Gender;
import com.holub.application.domain.member.Grant;
import com.holub.application.domain.member.Member;
import com.holub.application.util.InputScanner;

import java.lang.reflect.Field;
import java.util.List;

public class MainManagerView extends MainView{

    private final InputScanner scanner = InputScanner.getInstance();

    // 메뉴 출력
    public void showMenu() {
        System.out.println("(0)로그 아웃 (1)내 정보 (2)회원 정보/수정 (3)대출 정보/연장");
        System.out.println("(4)책 검색 (5)책 정렬 (6)책 등록 (7)책 수정");
        System.out.println("(Q)이전 페이지 (W)다음 페이지 ");
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

    public Grant getGrant(){
        System.out.println("계급을 선택해 주세요 (1) Member, (2) Manager");
        String n = getString();
        if(n.equals("1"))
            return Grant.Member;
        if(n.equals("2"))
            return Grant.Manager;
        System.out.println("1,2중 하나를 입력 해 주세요");
        return getGrant();
    }

    public Member viewOfUpdateMember(Member member){
        Grant grant = getGrant();
        System.out.println("id를 입력해 주세요");
        String id = getString();
        System.out.println("password를 입력해 주세요");
        String password = getString();
        System.out.println("name을 입력해 주세요");
        String name = getString();
        System.out.println("age를 입력해 주세요");
        Integer age = getInteger();
        System.out.println("gender를 선택해 주세요 (1)남자 (2)여자 (3)둘 다 아님");
        Integer selectedGender = getInteger();
        Gender gender;
        if (selectedGender == 1) {
            gender = Gender.Male;
        } else if (selectedGender == 2){
            gender = Gender.Female;
        } else {
            gender = Gender.None;
        }
        System.out.println("number를 입력해 주세요");
        String number = getString();

        return new Member(member.getUuid(), id, password, name, age, gender, number, grant);
    }

    // TODO -> 이거 따로 빼는게 좋을것 같음 중복 된다.
    public boolean isSelectedMenuValid(String selectedMenu) {
        switch(selectedMenu){
            case "1": case "2": case "3":
            case "4": case "5": case "Q":
            case "W": case "q": case "w":
            case "6": case "7": case "0":
                return true;
            default:
                return false;
        }
    }
}
