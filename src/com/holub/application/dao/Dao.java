package com.holub.application.dao;

import java.util.List;
import com.holub.database.Table;

public abstract class Dao {

    Table table;
    String baseUrl;

    public Dao(String url){
        baseUrl = url;
    }

    public void saveTable() { // expoter 사용해서 파일로 저장
        // TODO
    }
    public void loadTable(String tableName) { // impoter 사용해서 파일 로드 -> table 생성
        // TODO
    }

    abstract List<Object> selectTable(List<Integer> uuidList, String[] callName, int[] asc);
    abstract void insertTable(List<Object> domainList);
    abstract void updateTable(Integer uuid, Object updateInfo);
    abstract void returnTable();


}
