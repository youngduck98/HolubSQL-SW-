package com.holub.application.dao;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import com.holub.database.*;
import com.holub.tools.ArrayIterator;

public abstract class Dao {

    Table table;
    String baseUrl;

    public Dao(String url, String extension){
        baseUrl = url;
    }

    public void saveTable() throws IOException { // expoter 사용해서 파일로 저장
        Writer out = new FileWriter(table.name() + "." + "csv");
        Table.Exporter exporter = new CSVExporter(out);
        table.export(exporter);
        // TODO
    }
    public void loadTable(String tableName) throws IOException { // impoter 사용해서 파일 로드 -> table 생성
        Reader in = new FileReader(tableName + ".csv");
        table = TableFactory.create(new CSVImporter(in));
        in.close();
        // TODO
    }

    abstract List<Object> selectTable(List<Integer> uuidList, String[] callName, int[] asc);
    abstract void insertTable(List<Object> domainList);
    abstract void updateTable(Integer uuid, Object updateInfo);
    abstract void returnTable();


}
