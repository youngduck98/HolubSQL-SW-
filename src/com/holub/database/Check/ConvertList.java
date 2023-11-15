package com.holub.database.Check;

import java.util.ArrayList;
import java.util.List;

public class ConvertList {
    public static List<Comparable> convertToComparableList(List genericList) {
        List<Comparable> comparableList = new ArrayList<>();
        for (Object item : genericList) {
            TypeCheck.checkComparable(item, "");
            comparableList.add((Comparable) item);
        }
        return comparableList;
    }
}
