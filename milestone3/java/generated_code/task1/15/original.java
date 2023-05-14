@Override
    protected void doRow(FitRow row) throws Exception {
        currentCellParameter = FitUtils.extractCellParameter(row.cells().get(0));
        this.row = row;
        super.doRow(row);
    }