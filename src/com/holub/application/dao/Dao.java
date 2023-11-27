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
        // TODO
    }
    public void loadTable(String tableName) throws IOException { // impoter 사용해서 파일 로드 -> table 생성
        Reader in = new FileReader(baseUrl + "/"  + tableName + extension);
        table = TableFactory.create(new CSVImporter(in));
        in.close();
        // TODO
    }

    public abstract List<Object> selectTable(List<Integer> uuidList, String[] callName, int[] asc);
    public abstract void insertTable(List<Object> domainList);
    public abstract void updateTable(Object updateInfo) throws IOException;
    public abstract Table returnTable();
}
