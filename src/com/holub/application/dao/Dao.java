package com.holub.application.dao;

import java.io.*;
import java.util.List;

import com.holub.database.*;

public abstract class Dao {

    Table table;
    String baseUrl;
    String extension = "csv";

    public void saveTable() throws IOException { // expoter 사용해서 파일로 저장
        Writer out = new FileWriter(baseUrl + "/" +table.name() + "." + extension);
        Table.Exporter exporter = new CSVExporter(out);
        table.export(exporter);
        out.close();
        // TODO
    }
    public void loadTable(String tableName) throws IOException { // impoter 사용해서 파일 로드 -> table 생성
        Reader in = new FileReader(baseUrl + "/"  + tableName + "." + extension);
        table = TableFactory.create(new CSVImporter(in));
        in.close();
        // TODO
    }

    public void sortTable(String[] callName, int[] asc){
        if(callName != null && asc != null) {
            table = table.accept(new TableVisitorOrderBy(callName, asc));
            System.out.println("sorted!");
            return;
        }
        throw new IllegalArgumentException("sortTable");
    }

    public abstract List<Object> selectTableByUid(List<Integer> uuidList);
    public abstract void insertTable(List<Object> domainList) throws IOException;
    public abstract void updateTable(Object updateInfo) throws IOException;
    public abstract Table returnTable();
}
