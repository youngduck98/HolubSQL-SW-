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

public class ConcreteTableCustomTest {
    Random random = new Random();
    String[] baseColumName = {"a", "b", "c"};
    int dataNum = 5;

    public String[] MakeRightColNameFromBase(String type){
        String[] ret = new String[baseColumName.length];
        for(int i=0;i<baseColumName.length;i++){
            ret[i] = type + "(" + baseColumName[i] + ")";
        }
        return ret;
    }

    @Test
    public void makeMapTest(){
        ConcreteTable table = new ConcreteTable("test", baseColumName);
        List<List<Object>> rightmap = new ArrayList<>();

        for(int rowIndex=0;rowIndex<3;rowIndex++){
            List<Object> col = new ArrayList<>();
            for(int i=0;i<baseColumName.length;i++){
                col.add(random.nextInt(100));
            }
            rightmap.add(col);
            table.insert(col);
        }
        assertThat(table.makeTableToList()).isEqualTo(rightmap);
    }

    private boolean TableIsEqual(ConcreteTable t1, ConcreteTable t2){
        String[] t1ColumNames = t1.getColumnNames();
        String[] t2ColumNames = t2.getColumnNames();

        ArrayCheck.checkSame(t1ColumNames, t2ColumNames, "");

        List<List<Object>> map1 = t1.makeTableToList();
        List<List<Object>> map2 = t2.makeTableToList();

        return map1.equals(map2);
    }

    @Test
    public void AvgAggregation(){
        Random random = new Random();

        int dataNum = 5;
        List<AggregationFunction> checkFunctions = new ArrayList<>();
        for(int i=0;i<baseColumName.length;i++){
            checkFunctions.add(new Avg());
        }

        String[] rightColumName = MakeRightColNameFromBase("Avg");
        Double[] rightValue = {0.0, 0.0, 0.0};
        Table rightTable = TableFactory.create("right", rightColumName);


        Table testTable = TableFactory.create("test", baseColumName);

        for(int rowIndex=0;rowIndex<dataNum-1;rowIndex++){
            List<Object> col = new ArrayList<>();
            for(int i=0;i<baseColumName.length;i++){
                Integer randNum = random.nextInt(100);
                col.add(randNum);
                rightValue[i] += randNum;
            }
            testTable.insert(col);
        }
        testTable.insert(new Object[]{1, null, null});
        rightValue[0] += 1;

        for(int i=0;i<rightColumName.length;i++){
            rightValue[i] /= dataNum;
        }
        rightTable.insert(rightValue);

        Table table = testTable.applyAggregation(checkFunctions);

        assertThat(TableIsEqual((ConcreteTable) table, (ConcreteTable) rightTable)).isEqualTo(true);
    }

    @Test
    public void CountAggregation(){
        List<AggregationFunction> checkFunctions = new ArrayList<>();
        for(int i=0;i<baseColumName.length;i++){
            checkFunctions.add(new Count());
        }

        String[] rightColumName = {"Count(a)","Count(b)", "Count(c)"};
        Object[] rightValue = {5, 4, 4};
        Table rightTable = TableFactory.create("right", rightColumName);
        rightTable.insert(rightValue);

        Table testTable = TableFactory.create("test", baseColumName);

        for(int rowIndex=0;rowIndex<dataNum-1;rowIndex++){
            List<Object> col = new ArrayList<>();
            for(int i=0;i<baseColumName.length;i++){
                col.add(random.nextInt(100));
            }
            testTable.insert(col);
        }
        testTable.insert(new Object[]{1, null, null});
        Table table = testTable.applyAggregation(checkFunctions);

        assertThat(TableIsEqual((ConcreteTable) table, (ConcreteTable) rightTable)).isEqualTo(true);
    }

    @Test
    public void MaxAggregation(){
        Random random = new Random();

        int dataNum = 5;
        List<AggregationFunction> checkFunctions = new ArrayList<>();
        for(int i=0;i<baseColumName.length;i++){
            checkFunctions.add(new Max());
        }

        String[] rightColumName = MakeRightColNameFromBase("Max");
        Double[] rightValue = {0.0, 0.0, 0.0};
        Table rightTable = TableFactory.create("right", rightColumName);


        Table testTable = TableFactory.create("test", baseColumName);

        for(int rowIndex=0;rowIndex<dataNum-1;rowIndex++){
            List<Object> col = new ArrayList<>();
            for(int i=0;i<baseColumName.length;i++){
                Integer randNum = random.nextInt(100);
                col.add(Double.valueOf(randNum));
                if(rightValue[i] < randNum)
                    rightValue[i] = Double.valueOf(randNum);
            }
            testTable.insert(col);
        }
        testTable.insert(new Object[]{0, null, null});
        rightTable.insert(rightValue);

        Table table = testTable.applyAggregation(checkFunctions);

        assertThat(TableIsEqual((ConcreteTable) table, (ConcreteTable) rightTable)).isEqualTo(true);
    }
}
