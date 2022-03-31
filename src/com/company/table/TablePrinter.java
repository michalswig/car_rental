package com.company.table;

public class TablePrinter {
    public final static String MESSAGE_NOT_APPLICABLE = "NOT APPLICABLE";

    private final Table table;

    public TablePrinter(Table table) {
        this.table = table;
    }

    public void print() {
        printHeader();
        System.out.println();
        printRows();
    }

    private void printHeader() {
        for (HeaderColumn column : table.getHeader().getColumns()) {
            String emptyText = buildEmptyString(table.getMaxColumnWidth(table.getHeader().getColumns().indexOf(column)));
            printValue(emptyText, column.getValue());
        }
    }

    private void printRows() {
        for (Row row : table.getRows()) {
            for (RowColumn rowColumn : row.getRowColumns()) {
                String emptyText = buildEmptyString(table.getMaxColumnWidth(row.getRowColumns().indexOf(rowColumn)));
                printValue(emptyText, transformData(rowColumn));
                if (isLastColumn(row, rowColumn))
                    System.out.println();
            }
        }
    }

    private String transformData(RowColumn rowColumn) {
        String value = rowColumn.getValue();
        if (value == null) {
            value = MESSAGE_NOT_APPLICABLE;
        }
        return value;
    }

    private boolean isLastColumn(Row row, RowColumn rowColumn) {
        return Integer.valueOf(row.getRowColumns().indexOf(rowColumn)).equals(row.getRowColumns().size() - 1);
    }

    private void printValue(String emptyText, String value) {
        System.out.print(prepareValue(emptyText, value));
    }

    private String prepareValue(String emptyText, String value) {
        return value + emptyText.substring(0, emptyText.length() - value.length()) + "  ";
    }

    private String buildEmptyString(int maxWidth) {
        StringBuilder value = new StringBuilder();
        value.append(" ".repeat(Math.max(0, maxWidth)));
        value.setLength(maxWidth);
        return value.toString();
    }


}
