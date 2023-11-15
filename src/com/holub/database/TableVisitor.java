package com.holub.database;

public interface TableVisitor {
    Table visit(ConcreteTable table);
    Table visit(UnmodifiableTable table);
}
