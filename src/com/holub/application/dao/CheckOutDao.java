package com.holub.application.dao;

import com.holub.application.domain.book.Book;
import com.holub.application.domain.checkout.CheckOut;
import com.holub.database.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckOutDao extends Dao {
    private static CheckOutDao uniqueDao;
    private CheckOutDao(String url, String tableName) throws IOException {
        baseUrl = url;
        this.loadTable(tableName);
    }

    public static CheckOutDao getInstance(String url, String tableName) throws IOException {
        if(uniqueDao == null){
            uniqueDao = new CheckOutDao(url, tableName);
        }
        return uniqueDao;
    }

    public List<Object> selectTable(List<Integer> uuidList, String[] callName, int[] asc) {
        if(callName != null && asc != null)
            table.accept(new TableVisitorOrderBy(callName, asc));
        List<List<Object>> map = TableUtil.makeTableToList(table);
        Set<Object> uuidSet = new HashSet<>(uuidList);
        List<Object> newDataSet = new ArrayList<>();
        for(List<Object> row: map){
            if(!uuidSet.contains(row.get(0)))
                continue;
            newDataSet.add(new CheckOut(row));
        }

        return newDataSet;
    }

    public void insertTable(List<Object> domainList) {
        int nextUid = TableUtil.getHighIndex(table) + 1;
        for(Object checkOut: domainList){
            ((CheckOut)checkOut).setUuid(nextUid++);
            table.insert(((CheckOut)checkOut).toList());
        }
    }

    public void updateTable(Object updateInfo) throws IOException {
        Selector selector = new Selector.Adapter() {
            public boolean approve(Cursor[] tables) {
                return tables[0].column("uuid").equals(((CheckOut)updateInfo).getUuid());
            }
        };
        List<Object> row = TableUtil.makeTableToList(table.select(selector)).get(0);
        table.delete(selector);
        table.insert(row);
        saveTable();
        loadTable(table.name());
    }

    public List<Object> findCheckoutListFromUser(Integer userUid){
        Selector selector = new Selector.Adapter() {
            public boolean approve(Cursor[] tables) {
                return tables[0].column("memberUuid").equals(userUid);
            }
        };
        List<List<Object>> map = TableUtil.makeTableToList(table.select(selector));
        List<Object> ret = new ArrayList<>();
        for(List<Object> row: map){
            ret.add(new CheckOut(row));
        }
        return ret;
    }

    public Table returnTable() {
        return table;
    }
}
