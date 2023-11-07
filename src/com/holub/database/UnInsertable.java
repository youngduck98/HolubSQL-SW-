package com.holub.database;

import java.util.Collection;

public class UnInsertable extends GrantDecorator{

    public UnInsertable(Table wrapped) {
        this.wrapped = wrapped;
    }

    private final void illegal()    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int insert(String[] columnNames, Object[] values) {
        illegal();
        return 0;
    }

    @Override
    public int insert(Collection columnNames, Collection values) {
        illegal();
        return 0;
    }

    @Override
    public int insert(Object[] values) {
        illegal();
        return 0;
    }

    @Override
    public int insert(Collection values) {
        illegal();
        return 0;
    }
}
