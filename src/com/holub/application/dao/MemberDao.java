package com.holub.application.dao;

import com.holub.application.domain.member.Member;
import com.holub.database.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MemberDao extends Dao{
    private static MemberDao uniqueDao;
    private MemberDao(String url, String tableName) throws IOException {
        baseUrl = url;
        try {
            this.loadTable(tableName);
        }
        catch (FileNotFoundException e){
            table = TableFactory.create("Member", Member.getColNames());
            this.saveTable();
        }

    }

    public static MemberDao getInstance(String url, String tableName) throws IOException {
        if(uniqueDao == null){
            uniqueDao = new MemberDao(url, tableName);
        }
        return uniqueDao;
    }

    public static MemberDao getInstance(){
        if(uniqueDao == null)
            throw new NullPointerException();
        return uniqueDao;
    }

    public List<Object> selectTableByCol(List<Integer> nameList, String colName) {
        List<List<Object>> map = TableUtil.makeTableToList(table);

        int index = 0;
        String[] colNames = TableUtil.getColName(table);
        for(String col: colNames){
            if(col.equals(colName))
                break;
            if(colNames.length <= ++index)
                throw new IllegalArgumentException("no col name like that");
        }

        Set<Object> uuidSet = new HashSet<>(nameList);
        List<Object> newDataSet = new ArrayList<>();
        for(List<Object> row: map){
            if(!uuidSet.contains(row.get(0)))
                continue;
            newDataSet.add(new Member(row));
        }
        return newDataSet;
    }

    @Override
    public List<Object> selectTableByUid(List<Integer> uuidList) {
        List<List<Object>> map = TableUtil.makeTableToList(table);
        Set<Integer> uuidSet = new HashSet<>(uuidList);
        List<Object> newDataSet = new ArrayList<>();
        for(List<Object> row: map){
            if(!uuidSet.contains(Integer.parseInt(row.get(0).toString())))
                continue;
            newDataSet.add(new Member(row));
        }
        return newDataSet;
    }

    public void insertTable(List<Object> domainList) throws IOException {
        System.out.println(TableUtil.getHighIndex(table));
        int nextUid = TableUtil.getHighIndex(table) + 1;
        System.out.println("calculated uid for member");
        for(Object member: domainList){
            ((Member)member).setUuid(nextUid);
            table.insert(((Member)member).toList());
        }
        this.saveTable();
        this.loadTable(table.name());
    }

    public void updateTable(Object updateInfo) throws IOException {
        Selector selector = new Selector.Adapter() {
            public boolean approve(Cursor[] tables) {
                //System.out.println(tables[0].columnName(0));
                return tables[0].column("uuid").equals(((Member)updateInfo).getUuid().toString());
            }
        };
        table.delete(selector);
        table.insert(((Member)updateInfo).toList());
        saveTable();
        loadTable(table.name());
    }

    public Member findByIdAndPassword(String id, String password){
        Selector selector = new Selector.Adapter() {
            public boolean approve(Cursor[] tables) {
                return tables[0].column("id").equals(id) &&
                        tables[0].column("password").equals(password);
            }
        };
        List<List<Object>> rowList = TableUtil.makeTableToList(table.select(selector));
        if(rowList.isEmpty())
            return null;
        return new Member(rowList.get(0));
    }

    public List<Member> findAll(){
        List<List<Object>> rowList = TableUtil.makeTableToList(table);
        List<Member> ret = new ArrayList<>();
        for(List<Object> row: rowList){
            ret.add(new Member(row));
        }
        return ret;
    }

    public Table returnTable() {
        return table;
    }
}
