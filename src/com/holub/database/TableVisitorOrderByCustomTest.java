package com.holub.database;

import com.holub.database.Check.ArrayCheck;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class TableVisitorOrderByCustomTest {
    String[] baseColName = {"aaa", "bbb", "ccc"};

    public static ConcreteTable extractTable(Table table){
        if(table instanceof UnmodifiableTable)
            return extractTable(((UnmodifiableTable) table).extract());
        if(table instanceof ConcreteTable)
            return (ConcreteTable) table;
        return null;
    }

    public class ListComparator implements Comparator<List<Object>>{
        int index;
        int ascOrDesc;
        public ListComparator(int index, int ascOrDesc){
            this.index = index;
            this.ascOrDesc = ascOrDesc;
        }
        @Override
        public int compare(List<Object> o1, List<Object> o2) {
            if(o1.get(index) == null && o2.get(index) == null)
                return 0;
            if(o1.get(index) == null)
                return -1 * ascOrDesc;
            if(o2.get(index) == null)
                return 1 * ascOrDesc;
            return ((Comparable)o1.get(index)).compareTo(o2.get(index))*ascOrDesc;
        }
    }

    public Table sortTable(Table table, String[] colName, int[] asc){
        ConcreteTable cTable = extractTable(table);
        List<List<Object>> tableMap = cTable.makeTableToList();
        for(int i=0;i<colName.length;i++) {
            int index = TableUtil.findIndex(colName[i], baseColName);
            tableMap.sort(new ListComparator(index, asc[i]));
        }
        Table sortedTable = TableFactory.create("orderby" + Arrays.toString(colName), cTable.getColumnNames());
        for(List<Object> list: tableMap){
            sortedTable.insert(list);
        }
        return sortedTable;
    }

    @Test
    public void functionTestWithRandomInteger(){
        Table table = TableUtil.getAlreadyMakedIntegerTable(null, baseColName, 5);
        System.out.println("origin");
        TableUtil.showTableData((ConcreteTable) table);
        String[] colName = new String[]{"bbb", "aaa"};
        int[] asc = {-1, -1};

        Table testTable = table.accept(new TableVisitorOrderBy(colName, asc));
        Table answerTable = sortTable(table, colName, asc);
        System.out.println("output");
        TableUtil.showTableData((ConcreteTable) testTable);
        assertThat(TableUtil.TableIsEqual(
                (ConcreteTable) answerTable, (ConcreteTable) testTable)
        ).isEqualTo(true);
    }
    @Test
    public void functionTestWithRandomString(){
        Table table = TableUtil.getAlreadyMakedStringTable(null, baseColName, 5);
        System.out.println("origin");
        TableUtil.showTableData((ConcreteTable) table);
        String[] colName = new String[]{"bbb", "aaa"};
        int[] asc = {-1, -1};

        Table testTable = table.accept(new TableVisitorOrderBy(colName, asc));
        Table answerTable = sortTable(table, colName, asc);
        System.out.println("test");
        TableUtil.showTableData((ConcreteTable) testTable);
        assertThat(TableUtil.TableIsEqual(
                (ConcreteTable) answerTable, (ConcreteTable) testTable)
        ).isEqualTo(true);
    }

    @Test
    public void functionTestWithRandomAvailAtNull(){
        Table table = TableUtil.getAlreadyMakedIntegerTable(null, baseColName, 5);
        table.insert(new Object[]{1, 1, null});
        System.out.println("origin");
        TableUtil.showTableData((ConcreteTable) table);
        String[] colName = new String[]{"ccc"};
        int[] asc = {-1};

        Table testTable = table.accept(new TableVisitorOrderBy(colName, asc));
        Table answerTable = sortTable(table, colName, asc);
        System.out.println("output");
        TableUtil.showTableData((ConcreteTable) testTable);
        assertThat(TableUtil.TableIsEqual(
                (ConcreteTable) answerTable, (ConcreteTable) testTable)
        ).isEqualTo(true);
    }
}
