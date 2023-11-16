package com.holub.database;

import com.holub.database.Check.TypeCheck;
import com.sun.rowset.internal.Row;

import java.util.*;

public class TableVisitorOrderBy implements TableVisitor{
    public String[] colnames;
    public int[] asc;
    public int nowIndex;

    public TableVisitorOrderBy(String[] colname, int[] asc){
        this.colnames = colname;
        this.asc = asc;
        nowIndex = 0;
        RowComparator.colNames = colnames;
        RowComparator.asc = asc;
    }

    public static class RowComparator implements Comparator<List<Object>>{
        static String[] colNames;
        static int[] asc;
        static int nowIndex = 0;

        @Override
        public int compare(List<Object> list1, List<Object> list2) {
            if(list1.get(nowIndex) == null)
                return -1 * asc[nowIndex];
            if(list2.get(nowIndex) == null)
                return 1 * asc[nowIndex];
            TypeCheck.checkComparable(list1.get(nowIndex), "");
            TypeCheck.checkComparable(list2.get(nowIndex), "");
            Comparable v1 = (Comparable) list1.get(nowIndex);
            Comparable v2 = (Comparable) list2.get(nowIndex);

            return v1.compareTo(v2) * asc[nowIndex];
        }
    }

    public int findIndex(String colName, String[] colNames){
        int i=0;
        for(String value: colNames){
            if(value.equals(colName))
                return i;
            i++;
        }
        System.out.println(colName);
        throw new IllegalArgumentException("no name colName like that");
    }

    @Override
    public Table visit(ConcreteTable table) {
        List<List<Object>> map = table.makeTableToList();

        for(String colName: colnames){
            RowComparator.nowIndex = findIndex(colName, table.getColumnNames());
            System.out.println(RowComparator.nowIndex + " " + findIndex(colName, table.getColumnNames()));
            Collections.sort(map, new RowComparator());
        }

        Table ret = TableFactory.create("distinct", table.getColumnNames());

        for(List<Object> row: map){
            ret.insert(row);
        }

        return ret;
    }

    @Override
    public Table visit(UnmodifiableTable table) {
        if(table.extract() instanceof ConcreteTable)
            return this.visit((ConcreteTable) table.extract());
        return this.visit((UnmodifiableTable) table.extract());
    }
}
