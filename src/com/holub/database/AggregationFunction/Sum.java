package com.holub.database.AggregationFunction;

import com.holub.database.Check.ListCheck;
import com.holub.database.Check.TypeCheck;

import java.util.List;

public class Sum implements AggregationFunction{
    @Override
    public String convertColName(String str) {
        return "Sum(" + str + ")";
    }

    @Override
    public Object calculateValue(List values) {
        ListCheck.checkNotNull(values, "");
        if(values.isEmpty())
            return null;

        Double ret = 0.0;

        for(Object value: values){
            if(value == null)
                continue;
            TypeCheck.checkNumber(value, "");
            ret += Double.valueOf(value.toString());
        }

        return ret;
    }
}
