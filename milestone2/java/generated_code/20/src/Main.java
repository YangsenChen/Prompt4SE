public class Main {

    public static void main(String[] args) {
        // Replace with your own GeoPackage ID and FeatureCursor
        long geoPackageId = 123;
        FeatureCursor cursor = new FeatureCursor();

        // Index feature rows in GeoPackage database
        int count = indexRows(geoPackageId, cursor);

        // Print number of feature rows indexed
        System.out.println("Successfully indexed " + count + " feature rows.");
    }

    // Wrapper method for indexRows()
    private static int indexRows(long geoPackageId, FeatureCursor cursor) {
        int count = -1;
        try {
            while ((progress == null || progress.isActive()) && cursor.moveToNext()) {
                if (count < 0) {
                    count++;
                }
                try {
                    FeatureRow row = cursor.getRow();
                    if (row.isValid()) {
                        boolean indexed = index(geoPackageId, row, false);
                        if (indexed) {
                            count++;
                        }
                        if (progress != null) {
                            progress.addProgress(1);
                        }
                    }
                } catch (Exception e) {
                    Log.e(FeatureIndexer.class.getSimpleName(), "Failed to index feature. Table: "
                            + featureDao.getTableName() + ", Position: " + cursor.getPosition(), e);
                }
            }
        } finally {
            cursor.close();
        }
        return count;
    }

    // This method is assumed to be defined elsewhere in the codebase.
    // It is not included here since it should not be modified.
    private static boolean index(long geoPackageId, FeatureRow row, boolean b) {
        // Implementation not shown
        return true;
    }

    @Test
    public void testIndexRows() {
        // Replace with your own GeoPackage ID and FeatureCursor
        long geoPackageId = 123;
        FeatureCursor cursor = new FeatureCursor();

        // Call indexRows() and check that it returns a non-negative count
        int count = indexRows(geoPackageId, cursor);
        assertTrue(count >= 0);
    }

    @Test
    public void testIndexRowsWithNullCursor() {
        // Attempt to call indexRows() with a null cursor
        Exception exception = assertThrows(NullPointerException.class, () -> {
            indexRows(123, null);
        });
        assertEquals("Cursor cannot be null", exception.getMessage());
    }

    @Test
    public void testIndexRowsNoValidRows() {
        // Create a cursor with no valid rows
        FeatureCursor cursor = new FeatureCursor();
        cursor.addRow(new FeatureRow(false));

        // Call indexRows() and check that it returns 0
        int count = indexRows(123, cursor);
        assertEquals(0, count);
    }
}