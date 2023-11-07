package com.holub.database;

public class UnUpdatable extends GrantDecorator{

    public UnUpdatable(Table wrapped){
        this.wrapped = wrapped;
    }

    private final void illegal()    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(Selector where) {
        illegal();
        return 0;
    }
}
