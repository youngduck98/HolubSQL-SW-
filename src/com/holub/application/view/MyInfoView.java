package com.holub.application.view;

import com.holub.application.domain.member.Member;

import java.lang.reflect.Field;

public class MyInfoView {

    // 책 리스트 출력
    public static void showMyInfo(Member member) {
        // CTV에서 내 정보를 찾아서 출력
        Field[] fields = member.getClass().getFields();
        for(Field f: fields){
            System.out.printf("%7s", f.toString());
        }
        System.out.println();
        for(Object o: member.toList()){
            System.out.printf("%7s", o.toString());
        }
        System.out.println();
    }
}
