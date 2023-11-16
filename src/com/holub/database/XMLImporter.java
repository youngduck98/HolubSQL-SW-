/*  (c) 2004 Allen I. Holub. All rights reserved.
 *
 *  This code may be used freely by yourself with the following
 *  restrictions:
 *
 *  o Your splash screen, about box, or equivalent, must include
 *    Allen Holub's name, copyright, and URL. For example:
 *
 *      This program contains Allen Holub's SQL package.<br>
 *      (c) 2005 Allen I. Holub. All Rights Reserved.<br>
 *              http://www.holub.com<br>
 *
 *    If your program does not run interactively, then the foregoing
 *    notice must appear in your documentation.
 *
 *  o You may not redistribute (or mirror) the source code.
 *
 *  o You must report any bugs that you find to me. Use the form at
 *    http://www.holub.com/company/contact.html or send email to
 *    allen@Holub.com.
 *
 *  o The software is supplied <em>as is</em>. Neither Allen Holub nor
 *    Holub Associates are responsible for any bugs (or any problems
 *    caused by bugs, including lost productivity or data)
 *    in any of this code.
 */
package com.holub.database;

import com.holub.tools.ArrayIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import jdk.internal.org.xml.sax.InputSource;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

/***
 *	Pass this importer to a {@link Table} constructor (such
 *	as
 *	{link com.holub.database.ConcreteTable#ConcreteTable(Table.Importer)}
 *	to initialize
 *	a <code>Table</code> from
 *	a comma-sparated-value repressentation. For example:
 *	<PRE>
 *	Reader in = new FileReader( "people.csv" );
 *	people = new ConcreteTable( new CSVImporter(in) );
 *	in.close();
 *	</PRE>
 *	The input file for a table called "name" with
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
 *
 * @see Table
 * @see Table.Importer
 * @see CSVExporter
 */

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class XMLImporter implements Table.Importer {
    private BufferedReader in;
    private Document document;
    private NodeList rows;
    private int rowIndex;
    private String[] columnNames;
    private String tableName;

    public XMLImporter(Reader in) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Use ByteArrayInputStream with Reader
            String xmlString = readAllLines(in);
            ByteArrayInputStream stream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));

            // Parse the XML using InputStream
            this.document = builder.parse(stream);

            this.rows = document.getElementsByTagName("row");
            this.rowIndex = 0;
        } catch (ParserConfigurationException | SAXException e) {
            throw new IOException("Error parsing XML", e);
        }
    }

    private String readAllLines(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    public void startTable() throws IOException {
        Element root = document.getDocumentElement();
        this.tableName = root.getElementsByTagName("tableName").item(0).getTextContent();

        NodeList columnNodes = root.getElementsByTagName("column");
        this.columnNames = new String[columnNodes.getLength()];
        for (int i = 0; i < columnNodes.getLength(); i++) {
            Element columnElement = (Element) columnNodes.item(i);
            this.columnNames[i] = columnElement.getTextContent();
        }
    }

    public String loadTableName() {
        return tableName;
    }

    public int loadWidth() {
        return columnNames.length;
    }

    public Iterator loadColumnNames() {
        return new ArrayIterator(columnNames);
    }

    public Iterator loadRow() {
        if (rowIndex < rows.getLength()) {
            Element rowElement = (Element) rows.item(rowIndex++);
            NodeList cellNodes = rowElement.getElementsByTagName("cell");
            String[] rowData = new String[cellNodes.getLength()];
            for (int i = 0; i < cellNodes.getLength(); i++) {
                Element cellElement = (Element) cellNodes.item(i);
                rowData[i] = cellElement.getTextContent();
            }
            return new ArrayIterator(rowData);
        } else {
            return null;
        }
    }

    public void endTable() throws IOException {
        // You may perform any necessary cleanup here
        if (in != null) {
            in.close();
        }
    }
}