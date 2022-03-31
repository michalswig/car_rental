package com.company.table;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Table {
    private final Header header;
    private final List<Row> rows;

    public Table(Header header, List<Row> rows) {
        this.header = header;
        this.rows = rows;
    }

    public Header getHeader() {
        return header;
    }

    public List<Row> getRows() {
        return rows;
    }

    public Integer getMaxColumnWidth(int columnIndex) {
        Set<String> values = new HashSet<>();
        values.add(header.getColumns().get(columnIndex).getValue());
        for (Row row : rows) {
            String value = row.getRowColumns().get(columnIndex).getValue();
            if (row.getRowColumns().get(columnIndex).getValue() == null) {
                value = TablePrinter.MESSAGE_NOT_APPLICABLE;
            }
            values.add(value);
        }
        return values.stream().map(String::length).max(Integer::compare).get();
    }

}
