package com.holub.database;

public class UnDeletable extends GrantDecorator{

    private final void illegal()    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(Selector where) {
        illegal();
        return 0;
    }
}
