package com.holub.application.domain.member;

import java.util.Arrays;
import java.util.List;

public class Member {

    private Integer uuid;
    private String id;
    private String password;
    private String name;
    private Integer age;
    private Gender gender;
    private String number;
    private Grant grant;

    public Member(Integer uuid, String id, String password, String name, Integer age, Gender gender, String number, Grant grant) {
        this.uuid = uuid;
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.number = number;
        this.grant = grant;
    }

    public Member(List<Object> row) {
        this.uuid = (Integer) row.get(0);
        this.id = (String) row.get(1);
        this.password = (String) row.get(2);
        this.name = (String) row.get(3);
        this.age = (Integer) row.get(4);
        this.gender = (Gender) row.get(5);
        this.number = (String) row.get(6);
        this.grant = (Grant) row.get(7);
    }

    public List<Object> toList() {
        return Arrays.asList(
                uuid, id, password, name, age, gender, number, grant
        );
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Member(Integer uuid){
        this.uuid = uuid;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Grant getGrant() {
        return grant;
    }

    public void setGrant(Grant grant) {
        this.grant = grant;
    }
}
