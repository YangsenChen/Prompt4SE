import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Main {
    private FitRow row;
    private String currentCellParameter;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        FitRow row = new FitRow(new String[] {"cell1", "cell2", "cell3"});
        main.doRow(row);
    }

    protected void doRow(FitRow row) throws Exception {
        currentCellParameter = FitUtils.extractCellParameter(row.cells().get(0));
        this.row = row;
        super.doRow(row);
    }

    @Test
    void testDoRow() {
        FitRow row = new FitRow(new String[] {"param1", "param2", "param3"});
        Assertions.assertDoesNotThrow(() -> doRow(row), "Should not throw an exception");
        Assertions.assertEquals("param1", currentCellParameter, "Incorrect current cell parameter");
        Assertions.assertEquals(row, this.row, "Incorrect FitRow object");
    }

    @Test
    void testDoRow_exception() {
        FitRow row = new FitRow(new String[0]); // invalid row
        Assertions.assertThrows(Exception.class, () -> doRow(row), "Should throw an exception");
        Assertions.assertNull(currentCellParameter, "Current cell parameter should be null");
        Assertions.assertNull(this.row, "FitRow object should be null");
    }

    @Test
    void testExtractCellParameter() {
        String cell = "param1";
        FitRow row = new FitRow(new String[] {cell, "param2", "param3"});
        Assertions.assertEquals(cell, FitUtils.extractCellParameter(row.cells().get(0)), "Incorrect cell parameter");
    }
}