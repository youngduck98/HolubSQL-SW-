package com.holub.database;

import com.holub.database.Check.ArrayCheck;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class TableVisitorOrderByCustomTest {
    String[] baseColName = {"aaa", "bbb", "ccc"};
    //random 숫자 데이터에서의 오름차순 작동 확인

    //random 문자 data에서의 오름차순 작동 확인

    //null값이 포함된 data에서 오름차순 작동 확인
    @Test
    public void functionTestWithRandom(){
        Table table = TableUtil.getAlreadyMakedIntegerTable(null, baseColName, 5);
        TableUtil.showTableData((ConcreteTable) table);
        String[] colName = new String[]{"bbb, aaa"};
        Table testTable = table.accept(new TableVisitorOrderBy(colName, new int[]{-1, -1, -1}));
        TableUtil.showTableData((ConcreteTable) testTable);
    }

    @Test
    public void functionTestWithRandomAvailAtNull(){
        Table table = TableUtil.getAlreadyMakedIntegerTable(null, baseColName, 5);
        table.insert(new Object[]{1, 1, null});
        TableUtil.showTableData((ConcreteTable) table);
        String[] colName = new String[]{"bbb", "ccc"};
        Table testTable = table.accept(new TableVisitorOrderBy(colName, new int[]{0, 1, 1}));
        TableUtil.showTableData((ConcreteTable) testTable);
    }
}
