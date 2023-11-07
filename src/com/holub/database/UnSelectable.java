package com.holub.database;

import java.util.Collection;

public class UnSelectable extends GrantDecorator{

    private final void illegal()    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Table select(Selector where, String[] requestedColumns, Table[] other) {
        illegal();
        return null;
    }

    @Override
    public Table select(Selector where, String[] requestedColumns) {
        illegal();
        return null;
    }

    @Override
    public Table select(Selector where) {
        illegal();
        return null;
    }

    @Override
    public Table select(Selector where, Collection requestedColumns, Collection other) {
        illegal();
        return null;
    }

    @Override
    public Table select(Selector where, Collection requestedColumns) {
        illegal();
        return null;
    }

}
