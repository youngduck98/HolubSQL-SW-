package com.holub.database.AggregationFunction;

import com.holub.database.Check.ListCheck;
import com.holub.database.Check.TypeCheck;

import java.util.List;

public class Max implements AggregationFunction {
    @Override
    public String convertColName(String str) {
        return "Max(" + str + ")";
    }

    @Override
    public Object calculateValue(List values) {
        ListCheck.checkNotNull(values, "");
        if(values.isEmpty())
            return null;

        Comparable max = null;

        for(Object value: values){
            if(value == null)
                continue;
            TypeCheck.checkComparable(value, "");
            if(max == null) {
                max = (Comparable) value;
                continue;
            }
            if(max.getClass() != value.getClass()) {
                TypeCheck.checkNumber(max, "");
                TypeCheck.checkNumber(value, "");
                max = Double.valueOf(max.toString());
                value = Double.valueOf(value.toString());
            }
            if(max.compareTo(value) < 0){
                max = (Comparable) value;
            }
        }

        return max;
    }
}
