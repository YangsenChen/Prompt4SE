@Override
protected void doRow(FitRow row) throws Exception {
    List<Cell> cells = row.cells();
    String cellValue = extractCellValue(cells);
    if (shouldProcessRow(cellValue)) {
        processRow(row, cellValue);
    } else {
        skipRow(row);
    }
}

private String extractCellValue(List<Cell> cells) {
    Cell firstCell = cells.get(0);
    // Extract the cell value here
    return "";
}

private boolean shouldProcessRow(String cellValue) {
    // Return true if the row should be processed, false otherwise
    return true;
}

private void processRow(FitRow row, String cellValue) throws Exception {
    String transformedValue = transformCellValue(cellValue);
    doSomethingWith(transformedValue);
    this.row = row;
    super.doRow(row);
}

private String transformCellValue(String cellValue) {
    // Transform the cell value here
    return "";
}

private void skipRow(FitRow row) {
    // Skip the row here
}