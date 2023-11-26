package com.holub.database.AggregationFunction;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AggregationFunctionTest {
    @Test
    public void Avg(){
        List<Object> a = new ArrayList<>();
        AggregationFunction k = new Avg();
        a.add(1);
        a.add(2);
        assertThat(k.calculateValue(a)).isEqualTo(1.5);
    }

    @Test
    public void checkErrorInputAvg(){
        List<Object> a = new ArrayList<>();
        AggregationFunction k = new Avg();
        a.add("a");
        a.add("b");
        assertThatThrownBy(() ->
                k.calculateValue(a)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void Count(){
        List<Object> a = new ArrayList<>();
        AggregationFunction k = new Count();
        a.add(1);
        a.add(2);
        assertThat(k.calculateValue(a)).isEqualTo(2);
    }

    @Test
    public void checkErrorInputCount(){
        List<Object> a = null;
        AggregationFunction k = new Avg();
        assertThatThrownBy(() ->
                k.calculateValue(a)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void MaxInt(){
        List<Object> a = new ArrayList<>();
        AggregationFunction k = new Max();
        a.add(1);
        a.add(2);
        assertThat(k.calculateValue(a)).isEqualTo(2);
    }

    @Test
    public void MaxStr(){
        List<Object> a = new ArrayList<>();
        AggregationFunction k = new Max();
        a.add("a");
        a.add("b");
        assertThat(k.calculateValue(a)).isEqualTo("b");
    }

    @Test
    public void MaxInvaildInputObject(){
        List<Object> a = new ArrayList<>();
        AggregationFunction fuc = new Min();
        AggregationFunction max = new Max();
        a.add(fuc);
        assertThatThrownBy(() ->
                max.calculateValue(a)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void MaxInvaildInputIncompatible(){
        List<Object> a = new ArrayList<>();
        AggregationFunction max = new Max();
        a.add("a");
        a.add(1);
        assertThatThrownBy(() ->
                max.calculateValue(a)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void MinInt(){
        List<Object> a = new ArrayList<>();
        AggregationFunction k = new Min();
        a.add(1);
        a.add(2);
        assertThat(k.calculateValue(a)).isEqualTo(1);
    }

    @Test
    public void MinStr(){
        List<Object> a = new ArrayList<>();
        AggregationFunction k = new Min();
        a.add("a");
        a.add("b");
        assertThat(k.calculateValue(a)).isEqualTo("a");
    }

    @Test
    public void MinInvaildInputObject(){
        List<Object> a = new ArrayList<>();
        AggregationFunction fuc = new Max();
        AggregationFunction max = new Min();
        a.add(fuc);
        assertThatThrownBy(() ->
                max.calculateValue(a)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void minInvaildInputIncompatible(){
        List<Object> a = new ArrayList<>();
        AggregationFunction max = new Min();
        a.add("a");
        a.add(1);
        assertThatThrownBy(() ->
                max.calculateValue(a)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void Sum(){
        List<Object> a = new ArrayList<>();
        AggregationFunction k = new Sum();
        a.add(1);
        a.add(2);
        assertThat(k.calculateValue(a)).isEqualTo(3.0);
    }

    @Test
    public void checkErrorInputSum(){
        LinkedList<Object> a = new LinkedList<>();
        AggregationFunction k = new Sum();
        a.add("a");
        a.add("b");
        assertThatThrownBy(() ->
                k.calculateValue(a)).isInstanceOf(IllegalArgumentException.class);
    }


}