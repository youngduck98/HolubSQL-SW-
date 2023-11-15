package com.holub.database.Check;

import java.util.List;

public class ListCheck {
    public static void checkNotNull(List<?> list, String Msg){
        if(list == null)
            throw new IllegalArgumentException(Msg);
    }

    public static void checkListIsEmpty(List<?> list, String msg){
        if(list.isEmpty())
            throw new IllegalArgumentException(msg);
    }
}
