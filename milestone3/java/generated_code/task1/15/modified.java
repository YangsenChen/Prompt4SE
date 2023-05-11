@Override
protected void doRow(FitRow row) throws Exception {
    Cell cell = row.cells().get(0);
    currentCellParameter = extractCellParameter(cell);
    processRow(row);
}

private Cell extractCellParameter(Cell cell) {
    // implementation of extractCellParameter method
}

private void processRow(FitRow row) throws Exception {
    this.row = row;
    super.doRow(row);
}