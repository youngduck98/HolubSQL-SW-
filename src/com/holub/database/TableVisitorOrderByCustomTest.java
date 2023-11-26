package com.holub.database;

import com.holub.database.Check.ArrayCheck;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class TableVisitorOrderByCustomTest {
    String[] baseColName = {"aaa", "bbb", "ccc"};
    @Test
    public void functionTestWithRandomInteger(){
        Table table = TableUtil.getAlreadyMakedStringTable(null, baseColName, 5);
        TableUtil.showTableData((ConcreteTable) table);
        String[] colName = new String[]{"bbb", "aaa"};
        Table testTable = table.accept(new TableVisitorOrderBy(colName, new int[]{-1, -1}));
        TableUtil.showTableData((ConcreteTable) testTable);
    }
    @Test
    public void functionTestWithRandomString(){
        Table table = TableUtil.getAlreadyMakedIntegerTable(null, baseColName, 5);
        TableUtil.showTableData((ConcreteTable) table);
        String[] colName = new String[]{"bbb", "aaa"};
        Table testTable = table.accept(new TableVisitorOrderBy(colName, new int[]{-1, -1}));
        TableUtil.showTableData((ConcreteTable) testTable);
    }

    @Test
    public void functionTestWithRandomAvailAtNull(){
        Table table = TableUtil.getAlreadyMakedIntegerTable(null, baseColName, 5);
        table.insert(new Object[]{1, 1, null});
        TableUtil.showTableData((ConcreteTable) table);
        String[] colName = new String[]{"bbb"};
        Table testTable = table.accept(new TableVisitorOrderBy(colName, new int[]{1}));
        TableUtil.showTableData((ConcreteTable) testTable);
    }
}
