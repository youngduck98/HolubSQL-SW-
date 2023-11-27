package com.holub.database;

import com.holub.database.AggregationFunction.*;
import com.holub.database.Check.ArrayCheck;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ConcreteTableAggregationTest {
    Random random = new Random();
    String[] baseColumName = {"a", "b", "c"};
    int dataNum = 5;

    public String[] makeRightColNameFromBase(String type){
        String[] ret = new String[baseColumName.length];
        for(int i=0;i<baseColumName.length;i++){
            ret[i] = type + "(" + baseColumName[i] + ")";
        }
        return ret;
    }

    private boolean tableIsEqual(ConcreteTable t1, ConcreteTable t2){
        String[] t1ColumNames = t1.getColumnNames();
        String[] t2ColumNames = t2.getColumnNames();

        ArrayCheck.checkSame(t1ColumNames, t2ColumNames, "");

        List<List<Object>> map1 = t1.makeTableToList();
        List<List<Object>> map2 = t2.makeTableToList();

        return map1.equals(map2);
    }

    private String[] getRandomStringData(int num){
        String[] ret = new String[num];
        for(int i=0;i<num;i++)
            ret[i] = String.valueOf(random.nextInt(100));
        return ret;
    }

    private Number[] getRandomIntegerData(int num){
        Number[] ret = new Number[num];
        for(int i=0;i<num;i++)
            ret[i] = random.nextInt(100);
        return ret;
    }

    private Number[] addNumberArray(Number[] arr1, Number[] arr2){
        if(arr1.length != arr2.length)
            throw new IllegalArgumentException("not match length");
        Double[] ret = new Double[arr1.length];
        for(int i=0;i<arr1.length;i++){
            ret[i] = Double.valueOf(arr1[i].toString()) + Double.valueOf(arr2[i].toString());
        }
        return ret;
    }

    private Number[] maxNumberArray(Number[] arr1, Number[] arr2){
        if(arr1.length != arr2.length)
            throw new IllegalArgumentException("not match length");
        Number[] ret = new Number[arr1.length];
        for(int i=0;i<arr1.length;i++){
            if(arr1[i].getClass() != arr2[i].getClass()) {
                arr1[i] = Double.valueOf(arr1[i].toString());
                arr2[i] = Double.valueOf(arr2[i].toString());
            }
            if(((Comparable)arr1[i]).compareTo(arr2[i]) > 0) {
                ret[i] = arr1[i];
                continue;
            }
            ret[i] = arr2[i];
        }
        return ret;
    }

    private Number[] minNumberArray(Number[] arr1, Number[] arr2){
        if(arr1.length != arr2.length)
            throw new IllegalArgumentException("not match length");
        Number[] ret = new Number[arr1.length];
        for(int i=0;i<arr1.length;i++){
            if(arr1[i].getClass() != arr2[i].getClass()) {
                arr1[i] = Double.valueOf(arr1[i].toString());
                arr2[i] = Double.valueOf(arr2[i].toString());
            }
            if(((Comparable)arr1[i]).compareTo(arr2[i]) < 0) {
                ret[i] = arr1[i];
                continue;
            }
            ret[i] = arr2[i];
        }
        return ret;
    }

    private List<AggregationFunction>
    aggregationFunctionToList(AggregationFunction function, int num){
        List<AggregationFunction> ret = new ArrayList<>();
        for(int i=0;i<baseColumName.length;i++){
            ret.add(function);
        }
        return ret;
    }

    private Table getAlreadyMakedIntegerTable(String name, String[] colNames, int numOfData){
        Table ret = TableFactory.create(name, colNames);
        for(int i=0;i<numOfData;i++){
            ret.insert(getRandomIntegerData(colNames.length));
        }
        return ret;
    }

    private Double[] makeSumDataFromTable(ConcreteTable t){
        List<List<Object>> map = t.makeTableToList();
        Double[] ret = new Double[map.get(0).size()];
        Arrays.fill(ret, 0.0);

        for(List<Object> row:map){
            for(int i=0;i<row.size();i++)
                ret[i] += Double.valueOf(row.get(i).toString());
        }

        return ret;
    }

    private Comparable[] makeMaxDataFromTable(ConcreteTable t){
        List<List<Object>> map = t.makeTableToList();
        Comparable[] ret = new Comparable[map.get(0).size()];
        Arrays.fill(ret, 0);

        for(List<Object> row:map){
            for(int i=0;i<row.size();i++) {
                if (ret[i].getClass() != row.get(i).getClass()) {
                    ret[i] = Double.valueOf(ret[i].toString());
                    row.set(i, Double.valueOf(row.get(i).toString()));
                }
                if (ret[i].compareTo(row.get(i)) < 0)
                    ret[i] = (Comparable) row.get(i);
            }
        }
        return ret;
    }

    private Comparable[] makeMinDataFromTable(ConcreteTable t){
        List<List<Object>> map = t.makeTableToList();
        Comparable[] ret = new Comparable[map.get(0).size()];
        Arrays.fill(ret, 200);

        for(List<Object> row:map){
            for(int i=0;i<row.size();i++) {
                if (ret[i].getClass() != row.get(i).getClass()) {
                    ret[i] = Double.valueOf(ret[i].toString());
                    row.set(i, Double.valueOf(row.get(i).toString()));
                }
                if (ret[i].compareTo(row.get(i)) > 0)
                    ret[i] = (Comparable) row.get(i);
            }
        }
        return ret;
    }

    private Double[] makeAvgDataFromTable(ConcreteTable t){
        Double[] ret = makeSumDataFromTable(t);
        for(int i=0;i< ret.length;i++)
            ret[i]/=dataNum;
        return ret;
    }

    private void showTableData(ConcreteTable t){
        for(List<Object> row : t.makeTableToList()){
            for(Object value: row){
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public Number[] applyDataToTableAndSum(Table table, Number[] sum, int num){
        for(int rowIndex=0;rowIndex<num;rowIndex++){
            Number[] col = getRandomIntegerData(baseColumName.length);
            table.insert(col);
            sum = addNumberArray(sum, col);
        }
        return sum;
    }

    public Number[] applyDataToTableAndMax(Table table, Number[] max, int num){
        for(int rowIndex=0;rowIndex<dataNum;rowIndex++){
            Number[] col = getRandomIntegerData(baseColumName.length);
            table.insert(col);
            max = maxNumberArray(max, col);
        }
        return max;
    }

    public Number[] applyDataToTableAndMin(Table table, Number[] max, int num){
        for(int rowIndex=0;rowIndex<dataNum;rowIndex++){
            Number[] col = getRandomIntegerData(baseColumName.length);
            table.insert(col);
            max = minNumberArray(max, col);
        }
        return max;
    }

    public Number[] divideArrayByInt(Number[] arr,int num){
        for(int i=0;i<arr.length;i++){
            arr[i] = Double.valueOf(arr[i].toString())/num;
        }
        return arr;
    }

    public Number[] applyDataToTableAndAvg(Table table, Number[] avg, int num){
        avg = applyDataToTableAndSum(table, avg, num);

        return divideArrayByInt(avg, num);
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

    @Test
    public void AvgAggregation(){
        String[] rightColumName = makeRightColNameFromBase("Avg");
        Table rightTable = TableFactory.create("right", rightColumName);
        Table testTable = TableFactory.create("test", baseColumName);

        Number[] rightValue = {0.0, 0.0, 0.0};
        rightValue = applyDataToTableAndAvg(testTable, rightValue, dataNum);
        rightTable.insert(rightValue);

        List<AggregationFunction> checkFunctions =
                aggregationFunctionToList(new Avg(), baseColumName.length);
        Table table = testTable.applyAggregation(checkFunctions);

        assertThat(tableIsEqual((ConcreteTable) table, (ConcreteTable) rightTable)).isEqualTo(true);
    }

    @Test
    public void AvgAggregationAvailAtNull() {
        Table table = getAlreadyMakedIntegerTable("base", baseColumName, dataNum);
        Table rightTable = TableFactory.create("right", makeRightColNameFromBase("Avg"));
        Object[] rightValue = makeSumDataFromTable((ConcreteTable) table);
        Object[] add = new Object[]{100, 100, null};

        for(int i=0;i<rightValue.length;i++) {
            if(add[i] != null)
                rightValue[i] = Double.valueOf(rightValue[i].toString()) +
                        Double.valueOf(add[i].toString());
            rightValue[i]= Double.valueOf(rightValue[i].toString())/(dataNum+1);
        }
        rightTable.insert(rightValue);
        table.insert(add);

        Table testTable = table.applyAggregation(aggregationFunctionToList(new Avg(), baseColumName.length));
        assertThat(tableIsEqual((ConcreteTable) testTable, (ConcreteTable) rightTable)).isEqualTo(true);
    }
    @Test
    public void CountAggregation(){
        List<AggregationFunction> checkFunctions =
                aggregationFunctionToList(new Count(), baseColumName.length);

        String[] rightColumName = makeRightColNameFromBase("Count");
        Object[] rightValue = {5, 5, 5};
        Table rightTable = TableFactory.create("right", rightColumName);
        rightTable.insert(rightValue);

        Table testTable = TableFactory.create("test", baseColumName);
        for(int rowIndex=0;rowIndex<dataNum;rowIndex++){
            Object[] col = getRandomIntegerData(baseColumName.length);
            testTable.insert(col);
        }
        Table table = testTable.applyAggregation(checkFunctions);

        assertThat(tableIsEqual((ConcreteTable) table, (ConcreteTable) rightTable)).isEqualTo(true);
    }
    @Test
    public void CountAggregationAvailAtNull(){
        Table table = getAlreadyMakedIntegerTable("base", baseColumName, dataNum-1);
        Table rightTable = TableFactory.create("right", makeRightColNameFromBase("Count"));
        Object[] rigthValue = {5,5,4};
        rightTable.insert(rigthValue);
        table.insert(new Object[]{1,1,null});
        List<AggregationFunction> aggregations = aggregationFunctionToList(new Count(), baseColumName.length);
        Table test = table.applyAggregation(aggregations);
        assertThat(tableIsEqual((ConcreteTable) test, (ConcreteTable) rightTable)).isEqualTo(true);
    }

    @Test
    public void MaxAggregation(){
        List<AggregationFunction> checkFunctions =
                aggregationFunctionToList(new Max(), baseColumName.length);

        String[] rightColumName = makeRightColNameFromBase("Max");
        Number[] rightValue = {0, 0, 0};
        Table rightTable = TableFactory.create("right", rightColumName);
        Table table = TableFactory.create("test", baseColumName);
        rightValue = applyDataToTableAndMax(table, rightValue, dataNum);
        rightTable.insert(rightValue);

        Table testTable = table.applyAggregation(checkFunctions);
        showTableData((ConcreteTable) testTable);
        showTableData((ConcreteTable) rightTable);

        assertThat(tableIsEqual((ConcreteTable) testTable, (ConcreteTable) rightTable)).isEqualTo(true);
    }

    @Test
    public void MaxAggregationAvailAtNull(){
        Table table = getAlreadyMakedIntegerTable("base", baseColumName, dataNum);
        Table rightTable = TableFactory.create("right", makeRightColNameFromBase("Max"));
        Object[] rightValue = makeMaxDataFromTable((ConcreteTable) table);

        Object[] add = new Object[]{200, 200, null};
        rightValue = new Object[]{200, 200, rightValue[2]};

        rightTable.insert(rightValue);
        table.insert(add);

        Table testTable = table.applyAggregation(aggregationFunctionToList(new Max(), baseColumName.length));
        assertThat(tableIsEqual((ConcreteTable) testTable, (ConcreteTable) rightTable)).isEqualTo(true);
    }

    @Test
    public void MinAggregation(){
        List<AggregationFunction> checkFunctions =
                aggregationFunctionToList(new Min(), baseColumName.length);
        String[] rightColumName = makeRightColNameFromBase("Min");
        Table rightTable = TableFactory.create("right", rightColumName);
        Table table = TableFactory.create("test", baseColumName);

        Number[] rightValue = {200, 200, 200};
        rightValue = applyDataToTableAndMin(table, rightValue, dataNum);
        rightTable.insert(rightValue);

        Table testTable = table.applyAggregation(checkFunctions);
        assertThat(tableIsEqual((ConcreteTable) testTable, (ConcreteTable) rightTable)).isEqualTo(true);
    }

    @Test
    public void MinAggregationAvailAtNull(){
        Table table = getAlreadyMakedIntegerTable("base", baseColumName, dataNum);
        Table rightTable = TableFactory.create("right", makeRightColNameFromBase("Min"));
        Object[] rightValue = makeMinDataFromTable((ConcreteTable) table);
        rightTable.insert(rightValue);

        Table testTable = table.applyAggregation(aggregationFunctionToList(new Min(), baseColumName.length));
        showTableData((ConcreteTable) rightTable);
        showTableData((ConcreteTable) testTable);
        assertThat(tableIsEqual((ConcreteTable) testTable, (ConcreteTable) rightTable)).isEqualTo(true);
    }

    @Test
    public void SumAggregation(){
        List<AggregationFunction> checkFunctions =
                aggregationFunctionToList(new Sum(), baseColumName.length);

        String[] rightColumName = makeRightColNameFromBase("Sum");
        Table rightTable = TableFactory.create("right", rightColumName);
        Table testTable = TableFactory.create("test", baseColumName);

        Number[] rightValue = {0.0, 0.0, 0.0};
        rightValue = applyDataToTableAndSum(testTable, rightValue, dataNum);
        rightTable.insert(rightValue);

        Table table = testTable.applyAggregation(checkFunctions);
        assertThat(tableIsEqual((ConcreteTable) table, (ConcreteTable) rightTable)).isEqualTo(true);
    }

    @Test
    public void SumAggregationAvailAtNull() {
        Table table = getAlreadyMakedIntegerTable("base", baseColumName, dataNum);
        Table rightTable = TableFactory.create("right", makeRightColNameFromBase("Sum"));
        Object[] add = new Object[]{200, 200, null};
        Object[] rightValue = makeSumDataFromTable((ConcreteTable) table);

        for (int i = 0; i < rightValue.length; i++) {
            if (add[i] != null)
                rightValue[i] = Double.valueOf(rightValue[i].toString()) +
                        Double.valueOf(add[i].toString());
        }
        rightTable.insert(rightValue);
        table.insert(add);

        Table testTable = table.applyAggregation(
                aggregationFunctionToList(new Sum(), baseColumName.length));
        assertThat(tableIsEqual((ConcreteTable) testTable, (ConcreteTable) rightTable)).isEqualTo(true);
    }
}
