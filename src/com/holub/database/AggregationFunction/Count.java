package com.holub.database.AggregationFunction;

import com.holub.database.Check.ListCheck;

import java.util.List;

public class Count implements AggregationFunction {
    @Override
    public String convertColName(String str) {
        return "Count(" + str + ")";
    }

    @Override
    public Integer calculateValue(List values) {
        ListCheck.checkNotNull(values, "");//후에 에러 메시지 어떻게 처리 할 것인지 넣기
        int count = 0;
        for(Object value: values){
            if(value == null)
                continue;
            count++;
        }
        return count;
    }
}
