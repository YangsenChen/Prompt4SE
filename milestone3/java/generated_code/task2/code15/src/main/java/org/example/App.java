package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class FitRowParent {
    public void doRow(FitRow row) throws Exception {
        // Implement parent method
    }
}

class FitRow extends FitRowParent {
    private List<Cell> cells;

    public FitRow() {
        this.cells = new ArrayList<>();
    }

    public List<Cell> cells() {
        return this.cells;
//        return Collections.emptyList();

    }
}

class Cell {
    // assuming Cell is another class
}

class FitUtils {
    public static Object extractCellParameter(Cell cell) {
        // Implementation here
        return new Object(); // placeholder
    }
}

public class App extends FitRowParent {
    private FitRow row;
    private Object currentCellParameter;

    @Override
    public void doRow(FitRow row) throws Exception {
        currentCellParameter = FitUtils.extractCellParameter(row.cells().get(0));
        this.row = row;
        super.doRow(row);
    }

    public static void main(String[] args) {
        try {
            FitRow row = new FitRow();
            row.cells().add(new Cell());  // Adding a new cell to the row

            App main = new App();
            main.doRow(row);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
