package com.holub.database.Check;

public class ArrayCheck {
    public static void checkSame(Object[] o1, Object[] o2, String msg){
        if(o1.length != o2.length)
            throw new IllegalArgumentException("length not match, " + msg);
        for(int i=0;i<o1.length;i++){
            if(!o1[i].equals(o2[i])) {
                System.out.println((o1[i].toString() + o2[i].toString()));
                throw new IllegalArgumentException("not match object, " + msg);
            }
        }
    }
}
