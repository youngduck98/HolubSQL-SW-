package com.holub.database;

import com.holub.database.Check.TypeCheck;

import java.util.*;

public class TableVisitorOrderBy implements TableVisitor{
    public static class RowComparator implements Comparator<Iterator>{
        @Override
        public int compare(Iterator iter1, Iterator iter2) {
            int comp = 0;
            while(iter1.hasNext() && iter2.hasNext() && comp == 0){
                Object v1 = iter1.next();
                Object v2 = iter1.next();
                TypeCheck.checkComparable(v1, "");
                TypeCheck.checkComparable(v2, "");
                if(v1.getClass() == v2.getClass()){
                    comp = ((Comparable) v1).compareTo(v2);
                }
                comp = Double.valueOf(v1.toString()).compareTo(Double.valueOf(v2.toString()));
            }
            if(comp != 0)
                return comp;
            if(iter2.hasNext())
                return -1;
            return 1;
        }
    }
    @Override
    public Table visit(ConcreteTable table) {
        PriorityQueue<Iterator> que = new PriorityQueue<>(new RowComparator());
        Cursor cursor = table.rows();
        while(cursor.advance()){
            Iterator<Object> col = cursor.columns();
            que.add(col);
        }

        Table ret = TableFactory.create("distinct", table.getColumnNames());

        while(!que.isEmpty()){
            List<Object> list = new ArrayList<>();
            Iterator<Object> rowsCol = que.poll();
            while(rowsCol.hasNext()){
                list.add(rowsCol.next());
            }
            ret.insert(list);
        }
        return ret;
    }

    @Override
    public Table visit(UnmodifiableTable table) {
        if(table.extract() instanceof ConcreteTable)
            return this.visit((ConcreteTable) table.extract());
        return this.visit((UnmodifiableTable) table.extract());
    }
}
