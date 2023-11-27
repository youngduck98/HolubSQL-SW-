package com.holub.database;

import com.holub.database.AggregationFunction.AggregationFunction;
import com.holub.database.AggregationFunction.Avg;
import com.holub.database.AggregationFunction.Count;
import com.holub.database.AggregationFunction.Max;
import com.holub.database.Check.ArrayCheck;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class TableVistitorDistinctCustomTest {
    Random random = new Random();
    String[] baseColumName = {"a", "b", "c"};
    int dataNum = 5;

    private boolean TableIsEqual(ConcreteTable t1, ConcreteTable t2){
        String[] t1ColumNames = t1.getColumnNames();
        String[] t2ColumNames = t2.getColumnNames();

        ArrayCheck.checkSame(t1ColumNames, t2ColumNames, "");

        List<List<Object>> map1 = t1.makeTableToList();
        List<List<Object>> map2 = t2.makeTableToList();

        return map1.equals(map2);
    }

    private void printMap(List<List<Object>> map){
        for(List<Object> row: map){
            for(Object data: row){
                System.out.print(data.toString() + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void DistinctTest(){
        ConcreteTable rightTable = new ConcreteTable("right", baseColumName);
        String[] data1 = {"type1_data1", "type1_data2", "type1_data3"};
        String[] data2 = {"type2_data1", "type2_data2", "type2_data3"};
        rightTable.insert(data1);
        rightTable.insert(data2);

        ConcreteTable testTable = new ConcreteTable("test", baseColumName);
        for(int i=0;i<3;i++){
            testTable.insert(data1);
            testTable.insert(data2);
        }
        for(int i=0;i<2;i++){
            testTable.insert(data2);
            testTable.insert(data1);
        }

        TableVisitor visitor = new TableVisitorDistinct();
        assertThat(TableIsEqual((ConcreteTable)testTable.accept(visitor), rightTable)).isEqualTo(true);
    }

    @Test
    public void nullValueTest(){
        ConcreteTable rightTable = new ConcreteTable("right", baseColumName);
        String[] data1 = {"type1_data1", "type1_data2", null};
        String[] data2 = {"type2_data1", "type2_data2", "type2_data3"};
        rightTable.insert(data1);
        rightTable.insert(data2);

        ConcreteTable testTable = new ConcreteTable("test", baseColumName);
        for(int i=0;i<3;i++){
            testTable.insert(data1);
            testTable.insert(data2);
        }
        for(int i=0;i<2;i++){
            testTable.insert(data2);
            testTable.insert(data1);
        }

        TableVisitor visitor = new TableVisitorDistinct();
        assertThat(TableIsEqual((ConcreteTable)testTable.accept(visitor), rightTable)).isEqualTo(true);
    }

    @Test
    public void emptyTableTest(){
        Table table = TableFactory.create("test", baseColumName);
        TableVisitor visitor = new TableVisitorDistinct();
        table.accept(visitor);
    }
}
