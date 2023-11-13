package com.holub.database;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

/***
 *	Pass this exporter to a {@link Table#export} implementation to
 *	create a comma-sparated-value version of a {@link Table}.
 *	For example:
 *	<PRE>
 *	Table people  = TableFactory.create( ... );
 *	//...
 *	Writer out = new FileWriter( "people.csv" );
 *	people.export( new CSVExporter(out) );
 *	out.close();
 *	</PRE>
 *	The output file for a table called "name" with
 *	columns "first," "last," and "addrId" would look
 *	like this:
 *	<PRE>
 *	name
 *	first,	last,	addrId
 *	Fred,	Flintstone,	1
 *	Wilma,	Flintstone,	1
 *	Allen,	Holub,	0
 *	</PRE>
 *	The first line is the table name, the second line
 *	identifies the columns, and the subsequent lines define
 *	the rows.
 *
 * @include /etc/license.txt
 * @see Table
 * @see Table.Exporter
 * @see CSVImporter
 */

public class HTMLExporter implements Table.Exporter {
    private final Writer out;
    private int width;

    public HTMLExporter(Writer out) {
        this.out = out;
    }

    public void storeMetadata(String tableName, int width, int height, Iterator columnNames) throws IOException {
        this.width = width;

        out.write("<table>");
        out.write("\n");
        out.write("<caption>");
        out.write("\n");
        out.write(tableName == null ? "<anonymous>" : tableName);
        out.write("\n");
        out.write("</caption>");
        out.write("\n");

        out.write("<tr>");
        out.write("\n");
        while (columnNames.hasNext()) {
            Object columnName = columnNames.next();
            out.write("<th>");
            out.write(columnName == null ? "" : columnName.toString());
            out.write("</th>");
            out.write("\n");
        }
        out.write("</tr>");
        out.write("\n");
    }

    public void storeRow(Iterator data) throws IOException {
        out.write("<tr>");
        out.write("\n");
        int i = width;
        while (data.hasNext()) {
            Object datum = data.next();
            out.write("<td>");
            if (datum != null) {
                out.write(datum.toString());
            }
            out.write("</td>");

            if (--i > 0) {
                out.write("\n");
            }
        }
        out.write("</tr>");
    }

    public void startTable() throws IOException {
        // Nothing to do for the start of the table
    }

    public void endTable() throws IOException {
        out.write("\n");
        out.write("</table>");
    }
}
