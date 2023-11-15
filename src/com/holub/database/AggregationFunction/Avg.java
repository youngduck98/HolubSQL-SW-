package com.holub.database.AggregationFunction;

import com.holub.database.Check.ListCheck;
import com.holub.database.Check.TypeCheck;

import java.util.List;

public class Avg implements AggregationFunction{
    @Override
    public String convertColName(String str) {
        return "Avg(" + str + ")";
    }

    @Override
    public Object calculateValue(List values) {
        ListCheck.checkNotNull(values, "");
        if(values.isEmpty())
            return null;

        double ret = 0;
        for(Object value: values){
            if(value == null)
                continue;
            TypeCheck.checkNumber(value, "");
            double v =  Double.valueOf(value.toString());
            ret += v;
        }

        return ret/values.size();
    }
}
