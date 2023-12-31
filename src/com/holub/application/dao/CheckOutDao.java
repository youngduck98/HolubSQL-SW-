package com.holub.application.dao;

import com.holub.application.domain.book.Book;
import com.holub.application.domain.checkout.CheckOut;
import com.holub.database.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckOutDao extends Dao {
    private static CheckOutDao uniqueDao;
    private CheckOutDao(String url, String tableName) throws IOException {
        baseUrl = url;
        try {
            this.loadTable(tableName);
        }
        catch (FileNotFoundException f){
            table = TableFactory.create("CheckOut", CheckOut.getColNames());
            this.saveTable();
        }
    }

    public static CheckOutDao getInstance(String url, String tableName) throws IOException {
        if(uniqueDao == null){
            uniqueDao = new CheckOutDao(url, tableName);
        }
        return uniqueDao;
    }

    public static CheckOutDao getInstance(){
        if(uniqueDao == null)
            throw new NullPointerException();
        return uniqueDao;
    }

    @Override
    public List<Object> selectTableByUid(List<Integer> uuidList) {
        List<List<Object>> map = TableUtil.makeTableToList(table);
        Set<Object> uuidSet = new HashSet<>(uuidList);
        List<Object> newDataSet = new ArrayList<>();
        for(List<Object> row: map){
            if(!uuidSet.contains(Integer.parseInt(row.get(0).toString())))
                continue;
            newDataSet.add(new CheckOut(row));
        }
        return newDataSet;
    }

    public List<Object> selectTableByCol(List<String> nameList, String colName){
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
            if(!uuidSet.contains(row.get(index)))
                continue;
            newDataSet.add(new CheckOut(row));
        }
        return newDataSet;
    }

    public void insertTable(List<Object> domainList) throws IOException {
        int nextUid = TableUtil.getHighIndex(table) + 1;
        for(Object checkOut: domainList){
            ((CheckOut)checkOut).setUuid(nextUid++);
            table.insert(((CheckOut)checkOut).toList());
        }
        this.saveTable();
        this.loadTable(table.name());
    }

    public void updateTable(Object updateInfo) throws IOException {
        Selector selector = new Selector.Adapter() {
            public boolean approve(Cursor[] tables) {
                return tables[0].column("uuid").equals(((CheckOut)updateInfo).getUuid().toString());
            }
        };
        List<Object> row = TableUtil.makeTableToList(table.select(selector)).get(0);
        table.delete(selector);
        table.insert(((CheckOut)updateInfo).toList());
        saveTable();
        loadTable(table.name());
    }

    public List<Object> findCheckoutListFromUser(Integer userUid){
        Selector selector = new Selector.Adapter() {
            public boolean approve(Cursor[] tables) {
                return tables[0].column("memberUuid").equals(userUid.toString());
            }
        };
        List<List<Object>> map = TableUtil.makeTableToList(table.select(selector));
        List<Object> ret = new ArrayList<>();
        for(List<Object> row: map){
            ret.add(new CheckOut(row));
        }
        return ret;
    }

    public void deleteRow(Integer checkoutUUid){
        Selector selector = new Selector.Adapter() {
            public boolean approve(Cursor[] tables) {
                return tables[0].column("uuid").equals(checkoutUUid.toString());
            }
        };

        table.delete(selector);
    }

    public Table returnTable() {
        return table;
    }
}
