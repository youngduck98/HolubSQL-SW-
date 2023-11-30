package com.holub.application.view;

import com.holub.application.domain.member.Gender;
import com.holub.application.domain.member.Grant;
import com.holub.application.domain.member.Member;
import com.holub.application.util.InputScanner;


public class SignUpView {
    private final InputScanner scanner = InputScanner.getInstance();

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

    public Member execute() {
        // TODO -> validation 추가
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

        return new Member(id, password, name, age, gender, number, grant);
    }

}
