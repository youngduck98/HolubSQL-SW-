package com.holub.database;

public class UnDeletable extends GrantDecorator{

    public UnDeletable(Table wrapped) {
        this.wrapped = wrapped;
    }

    private final void illegal()    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(Selector where) {
        illegal();
        return 0;
    }
}
