package com.holub.database;

import com.holub.database.AggregationFunction.AggregationFunction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class GrantDecorator implements Table {

    protected Table wrapped;

    @Override
    public Object clone() throws CloneNotSupportedException {
        GrantDecorator copy = (GrantDecorator) super.clone();
        copy.wrapped = (Table)( wrapped.clone() );
        return copy;
    }

    @Override
    public String name() {
        return wrapped.name();
    }

    @Override
    public void rename(String newName) {
        wrapped.rename(newName);
    }

    @Override
    public boolean isDirty() {
        return wrapped.isDirty();
    }

    @Override
    public int insert(String[] columnNames, Object[] values) {
        return wrapped.insert(columnNames, values);
    }

    @Override
    public int insert(Collection columnNames, Collection values) {
        return wrapped.insert(columnNames, values);
    }

    @Override
    public int insert(Object[] values) {
        return wrapped.insert(values);
    }

    @Override
    public int insert(Collection values) {
        return wrapped.insert(values);
    }

    @Override
    public int update(Selector where) {
        return wrapped.update(where);
    }

    @Override
    public int delete(Selector where) {
        return wrapped.delete(where);
    }

    @Override
    public void begin() {
        wrapped.begin();
    }

    @Override
    public void commit(boolean all) throws IllegalStateException {
        wrapped.commit(all);
    }

    @Override
    public void rollback(boolean all) throws IllegalStateException {
        wrapped.rollback(all);
    }

    @Override
    public Table select(Selector where, String[] requestedColumns, Table[] other) {
        return wrapped.select(where, requestedColumns, other);
    }

    @Override
    public Table select(Selector where, String[] requestedColumns) {
        return wrapped.select(where, requestedColumns);
    }

    @Override
    public Table select(Selector where) {
        return wrapped.select(where);
    }

    @Override
    public Table select(Selector where, Collection requestedColumns, Collection other) {
        return wrapped.select(where, requestedColumns, other);
    }

    @Override
    public Table select(Selector where, Collection requestedColumns) {
        return wrapped.select(where, requestedColumns);
    }

    @Override
    public Table applyAggregation(List<AggregationFunction> aggregations) {
        return ((ConcreteTable)wrapped.applyAggregation(aggregations));
    }
    @Override
    public Cursor rows() {
        return wrapped.rows();
    }

    public Table extract() {
        return wrapped;
    }

    public void  export(Table.Exporter exporter) throws IOException {
        wrapped.export(exporter);
    }

    public String toString() {
        return wrapped.toString();
    }

}
