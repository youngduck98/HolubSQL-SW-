package com.holub.database;

import java.io.IOException;
import java.util.Collection;

public class NotAll extends GrantDecorator{

    public NotAll(Table wrapped) {
        this.wrapped = wrapped;
    }

    private final void illegal()    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        illegal();
        return null;
    }

    @Override
    public String name() {
        illegal();
        return "";
    }

    @Override
    public void rename(String newName) {
        illegal();
    }

    @Override
    public boolean isDirty() {
        illegal();
        return false;
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

    @Override
    public int update(Selector where) {
        illegal();
        return 0;
    }

    @Override
    public int delete(Selector where) {
        illegal();
        return 0;
    }

    @Override
    public void begin() {
        illegal();
    }

    @Override
    public void commit(boolean all) throws IllegalStateException {
        illegal();
    }

    @Override
    public void rollback(boolean all) throws IllegalStateException {
        illegal();
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

    @Override
    public Cursor rows() {
        illegal();
        return null;
    }

    @Override
    public Table extract() {
        illegal();
        return null;
    }

    @Override
    public void export(Exporter exporter) throws IOException {
        illegal();
    }

    @Override
    public String toString() {
        return "You do not have permission";
    }
}
