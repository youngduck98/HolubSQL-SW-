package com.holub.database;

import java.util.*;

public class TableVisitorDistinct implements TableVisitor{
    @Override
    public Table visit(ConcreteTable table) {
        HashSet<List<Object>> duplicated = new HashSet<>();

        Cursor cursor = table.rows();
        Table ret = TableFactory.create(null, table.getColumnNames());

        while(cursor.advance()){
            Iterator colIter = cursor.columns();
            List<Object> row = new ArrayList<>();
            while(colIter.hasNext()){
                row.add(colIter.next());
            }
            if(duplicated.contains(row)) {
                continue;
            }
            duplicated.add(row);
            ret.insert(row);
        }

        return ret;
    }

    @Override
    public Table visit(UnmodifiableTable table) {
        Table extractedTable = table.extract();
        if(extractedTable instanceof UnmodifiableTable){
            return visit((UnmodifiableTable) extractedTable);
        }
        if(extractedTable instanceof ConcreteTable)
            return visit((ConcreteTable)extractedTable);
        return null;
    }
}
