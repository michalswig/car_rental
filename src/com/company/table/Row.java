package com.company.table;

import java.util.ArrayList;
import java.util.List;

public class Row {
    List<RowColumn> rowColumns = new ArrayList<>();

    public void addColumn(RowColumn rowColumn) {
        rowColumns.add(rowColumn);
    }

    public List<RowColumn> getRowColumns() {
        return rowColumns;
    }
}
