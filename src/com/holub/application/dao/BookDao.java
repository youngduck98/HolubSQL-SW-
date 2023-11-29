package com.holub.application.dao;

import com.holub.application.domain.book.Book;
import com.holub.database.*;

import java.io.IOException;
import java.util.*;

public class BookDao extends Dao{
    private static BookDao uniqueDao;
    private BookDao(String url, String tableName) throws IOException {
        baseUrl = url;
        this.loadTable(tableName);
    }

    public static BookDao getInstance(String url, String tableName) throws IOException {
        if(uniqueDao == null){
            uniqueDao = new BookDao(url, tableName);
        }
        return uniqueDao;
    }

    public List<Object> sortTable(String[] callName, int[] asc){
        if(callName != null && asc != null)
            table.accept(new TableVisitorOrderBy(callName, asc));
        throw new IllegalArgumentException("sortTable");
    }

    public List<Object> selectTable(List<Integer> uuidList) {
        List<List<Object>> map = TableUtil.makeTableToList(table);
        Set<Object> uuidSet = new HashSet<>(uuidList);
        List<Object> newDataSet = new ArrayList<>();
        for(List<Object> row: map){
            if(!uuidSet.contains(row.get(0)))
                continue;
            newDataSet.add(new Book(row));
        }
        return newDataSet;
    }

    public void insertTable(List<Object> domainList) {
        int nextUid = TableUtil.getHighIndex(table) + 1;
        for(Object book: domainList){
            ((Book)book).setUuid(nextUid++);
            table.insert(((Book)book).toList());
        }
    }

    public void updateTable(Object updateInfo) throws IOException {
        Selector selector = new Selector.Adapter() {
            public boolean approve(Cursor[] tables) {
                return tables[0].column("uuid").equals(((Book)updateInfo).getUuid());
            }
        };
        List<Object> row = TableUtil.makeTableToList(table.select(selector)).get(0);
        table.delete(selector);
        table.insert(row);
        saveTable();
        loadTable(table.name());
    }

    public List<String> getTablesGenre(){
        Selector selector = new Selector.Adapter();
        Table distinctTable = table.select(selector, new String[]{"genre"})
                .accept(new TableVisitorDistinct());
        List<String> ret = new ArrayList<>();
        Cursor cursor = distinctTable.rows();
        while(cursor.advance()){
            Iterator a = cursor.columns();
            ret.add((String)a.next());
        }
        return ret;
    }

    public List<Book> getGenresBook(String genre){
        Selector selector = new Selector.Adapter() {
            public boolean approve(Cursor[] tables) {
                return tables[0].column("genre").equals(genre);
            }
        };
        List<Book> ret = new ArrayList<>();
        for(List<Object> row: TableUtil.makeTableToList(table.select(selector))){
            ret.add(new Book(row));
        }
        return ret;
    }

    public Table returnTable() {
        return table;
    }
}
