package com.holub.database.AggregationFunction;

import java.util.List;

public interface AggregationFunction {
    String convertColName(String str);
    Object calculateValue(List Values);
}
