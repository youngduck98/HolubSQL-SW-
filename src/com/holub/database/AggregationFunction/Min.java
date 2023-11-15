package com.holub.database.AggregationFunction;

import com.holub.database.Check.ListCheck;
import com.holub.database.Check.TypeCheck;

import java.util.List;

public class Min implements AggregationFunction {
    @Override
    public String convertColName(String str) {
        return "Min(" + str + ")";
    }

    @Override
    public Object calculateValue(List values) {
        ListCheck.checkNotNull(values, "");
        if(values.isEmpty())
            return null;

        Comparable min = null;
        for(Object value: values){
            if(value == null)
                continue;
            TypeCheck.checkComparable(value, "");
            if(min == null) {
                min = (Comparable) value;
                continue;
            }
            if(min.getClass() != value.getClass()) {
                min = Double.valueOf(min.toString());
                value = Double.valueOf(value.toString());
            }
            if(min.compareTo(value) > 0){
                min = (Comparable) value;
            }
        }

        return min;
    }
}
