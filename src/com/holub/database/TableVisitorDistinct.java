package com.holub.database;

import java.util.*;

public class TableVisitorDistinct implements TableVisitor{
    @Override
    public void visit(ConcreteTable table) {
        HashSet<String> duplicated = new HashSet<>();

        Cursor cursor = table.rows();

        while(cursor.advance()){
            Iterator colIter = cursor.columns();
            StringBuilder row = new StringBuilder();
            while(colIter.hasNext()){
                row.append(colIter.next());
            }
            if(duplicated.contains(row)) {
                colIter.remove();
                continue;
            }
            duplicated.add(row.toString());
        }
    }

    @Override
    public void visit(UnmodifiableTable table) {
        visit((ConcreteTable)table.extract());
    }
}
