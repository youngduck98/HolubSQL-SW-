package com.holub.application.dao;

import com.holub.application.domain.book.Book;
import com.holub.database.*;
import com.holub.database.AggregationFunction.AggregationFunction;
import com.holub.database.AggregationFunction.Max;
import com.holub.database.AggregationFunction.Min;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class BookDao extends Dao{
    private static BookDao uniqueDao;
    private BookDao(String url, String tableName) throws IOException {
        baseUrl = url;
        try {
            this.loadTable(tableName);
        }
        catch (FileNotFoundException e){
            table = TableFactory.create("Book", Book.getColumnames());
            this.saveTable();
        }
    }

    public static BookDao getInstance(String url, String tableName) throws IOException {
        if(uniqueDao == null){
            uniqueDao = new BookDao(url, tableName);
        }
        return uniqueDao;
    }

    public List<Object> selectTableByUid(List<Integer> uuidList) {
        List<List<Object>> map = TableUtil.makeTableToList(table);
        Set<Object> uuidSet = new HashSet<>(uuidList);
        List<Object> newDataSet = new ArrayList<>();
        for(List<Object> row: map){
            if(!uuidSet.contains(Integer.parseInt(row.get(0).toString())))
                continue;
            newDataSet.add(new Book(row));
        }
        return newDataSet;
    }

    public List<Object> selectTableByCol(Object[] nameList, String colName){
        try {
            return selectTableByCol(Arrays.asList(nameList), colName);
        }
        catch (IllegalArgumentException e){
            System.out.println("something wrong" + e);
            return new ArrayList<>();
        }
    }

    public List<Object> selectTableByCol(List<Object> nameList, String colName) {
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
            newDataSet.add(new Book(row));
        }
        return newDataSet;
    }

    public void insertTable(List<Object> domainList) throws IOException {
        int nextUid = TableUtil.getHighIndex(table) + 1;
        for(Object book: domainList){
            ((Book)book).setUuid(nextUid++);
            table.insert(((Book)book).toList());
        }
        this.saveTable();
        this.loadTable(table.name());
    }

    public void updateTable(Object updateInfo) throws IOException {
        Selector selector = new Selector.Adapter() {
            public boolean approve(Cursor[] tables) {
                return tables[0].column("uuid").equals(((Book)updateInfo).getUuid().toString());
            }
        };
        List<Object> row = TableUtil.makeTableToList(table.select(selector)).get(0);
        table.delete(selector);
        table.insert(((Book)updateInfo).toList());
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

    public List<Book> getBookList(){
        List<Book> ret = new ArrayList<>();
        for(List<Object> row: TableUtil.makeTableToList(table)){
            ret.add(new Book(row));
        }
        return ret;
    }

    public String getMaxValue(String colName){
        Selector selector = new Selector.Adapter() {};
        Table ret = table.select(selector, new String[]{colName});
        List<AggregationFunction> maxList = new ArrayList<>();
        maxList.add(new Max());
        ret = ret.applyAggregation(maxList);
        Cursor cursor = ret.rows();
        cursor.advance();
        return cursor.columns().next().toString();
        //return ret.rows().columns().next().toString();
    }

    public String getMinValue(String colName){
        Selector selector = new Selector.Adapter() {};
        Table ret = table.select(selector, new String[]{colName});
        List<AggregationFunction> minList = new ArrayList<>();
        minList.add(new Min());
        ret = ret.applyAggregation(minList);
        Cursor cursor = ret.rows();
        cursor.advance();
        return cursor.columns().next().toString();
        //return ret.rows().columns().next().toString();
    }

    public Table returnTable() {
        return table;
    }
}
