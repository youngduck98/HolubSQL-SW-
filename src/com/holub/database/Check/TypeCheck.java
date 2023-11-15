package com.holub.database.Check;

public class TypeCheck {
    public static void checkComparable(Object o, String msg){
        if(!(o instanceof Comparable))
            throw  new IllegalArgumentException(msg);
    }

    public static void checkNumber(Object o, String msg){
        if(!(o instanceof Number))
            throw  new IllegalArgumentException(msg);
    }
}
