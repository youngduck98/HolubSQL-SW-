package com.holub.database;

import com.holub.database.Check.TypeCheck;
import com.sun.rowset.internal.Row;

import java.util.*;

public class TableVisitorOrderBy implements TableVisitor{
    public String[] colnames;
    public int[] inputAsc;

    public TableVisitorOrderBy(String[] colname, int[] asc){
        this.colnames = colname;
        this.inputAsc = asc;
    }

    public class RowComparator implements Comparator<List<Object>>{
        int nowIndex;
        int ascOrDesc;

        public RowComparator(int nowIndex, int ascOrDesc){
            this.nowIndex = nowIndex;
            this.ascOrDesc = ascOrDesc;
        }
        @Override
        public int compare(List<Object> list1, List<Object> list2) {
            if(list1.get(nowIndex) == null)
                return -1 * ascOrDesc;
            if(list2.get(nowIndex) == null)
                return 1 * ascOrDesc;
            TypeCheck.checkComparable(list1.get(nowIndex), "");
            TypeCheck.checkComparable(list2.get(nowIndex), "");
            Comparable v1 = (Comparable) list1.get(nowIndex);
            Comparable v2 = (Comparable) list2.get(nowIndex);

            return v1.compareTo(v2) * ascOrDesc;
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
        for(int i=0;i<colnames.length;i++){
            int nowIndex = findIndex(colnames[i], table.getColumnNames());
            Collections.sort(map, new RowComparator(nowIndex, inputAsc[i]));
        }

        Table ret = TableFactory.create("orderBy" + Arrays.toString(colnames), table.getColumnNames());

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
