package com.company.table;

import java.util.ArrayList;
import java.util.List;

public class Header {
    private final List<HeaderColumn> columns = new ArrayList<>();

    public void addColumn(HeaderColumn headerColumn) {
        columns.add(headerColumn);
    }

    public List<HeaderColumn> getColumns() {
        return columns;
    }
}
