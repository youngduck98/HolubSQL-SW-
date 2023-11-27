package com.holub.application.dao;

import com.holub.application.domain.member.Member;
import com.holub.database.Cursor;
import com.holub.database.Selector;
import com.holub.database.Table;
import com.holub.database.TableUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MemberDao extends Dao{
    private static MemberDao uniqueDao;
    private MemberDao(String url, String tableName) throws IOException {
        baseUrl = url;
        this.loadTable(tableName);
    }

    public static MemberDao getInstance(String url, String tableName) throws IOException {
        if(uniqueDao == null){
            uniqueDao = new MemberDao(url, tableName);
        }
        return uniqueDao;
    }

    @Override
    List<Object> selectTable(List<Integer> uuidList, String[] callName, int[] asc) {
        List<List<Object>> map = TableUtil.makeTableToList(table);
        Set<Object> uuidSet = new HashSet<>(uuidList);
        List<List<Object>> newDataSet = new ArrayList<>();
        for(List<Object> row: map){
            if(!uuidSet.contains(row.get(0)))
                continue;
            newDataSet.add(row);
        }

        return null;
    }

    @Override
    void insertTable(List<Object> domainList) {
        for(Object member: domainList){
            table.insert(((Member)member).toList());
        }
    }

    @Override
    void updateTable(Object updateInfo) throws IOException {
        Selector selector = new Selector.Adapter() {
            public boolean approve(Cursor[] tables) {
                return tables[0].column("uuid").equals(((Member)updateInfo).getUuid());
            }
        };
        List<Object> row = TableUtil.makeTableToList(table.select(selector)).get(0);
        table.delete(selector);
        saveTable();
        loadTable(table.name());
    }

    @Override
    Table returnTable() {
        return table;
    }
}
