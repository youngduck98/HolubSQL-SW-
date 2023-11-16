package com.holub.database;

import com.holub.database.Check.ArrayCheck;

import java.util.List;
import java.util.Random;

public class TableUtil {
    public static void showTableData(ConcreteTable t){
        StringBuilder str = new StringBuilder();
        for(String colName: t.getColumnNames()){
            str.append(colName + " ");
        }
        System.out.println(str.toString());
        for(List<Object> row : t.makeTableToList()){
            for(Object value: row){
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static boolean TableIsEqual(ConcreteTable t1, ConcreteTable t2){
        String[] t1ColumNames = t1.getColumnNames();
        String[] t2ColumNames = t2.getColumnNames();

        ArrayCheck.checkSame(t1ColumNames, t2ColumNames, "");

        List<List<Object>> map1 = t1.makeTableToList();
        List<List<Object>> map2 = t2.makeTableToList();

        return map1.equals(map2);
    }

    public static String[] getRandomStringData(int num, Random random){
        String[] ret = new String[num];
        for(int i=0;i<num;i++)
            ret[i] = String.valueOf(random.nextInt(100));
        return ret;
    }

    public static Number[] getRandomIntegerData(int num, Random random){
        Number[] ret = new Number[num];
        for(int i=0;i<num;i++)
            ret[i] = random.nextInt(100);
        return ret;
    }

    public static Number[] addNumberArray(Number[] arr1, Number[] arr2){
        if(arr1.length != arr2.length)
            throw new IllegalArgumentException("not match length");
        Double[] ret = new Double[arr1.length];
        for(int i=0;i<arr1.length;i++){
            ret[i] = Double.valueOf(arr1[i].toString()) + Double.valueOf(arr2[i].toString());
        }
        return ret;
    }

    public static Table getAlreadyMakedIntegerTable(String name, String[] colNames, int numOfData){
        Table ret = TableFactory.create(name, colNames);
        Random random = new Random();
        for(int i=0;i<numOfData;i++){
            ret.insert(getRandomIntegerData(colNames.length, random));
        }
        return ret;
    }
}
