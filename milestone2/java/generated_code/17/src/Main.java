public class Main {

    public static void main(String[] args) {
        // create a mock TableMetadata object for testing purposes
        TableMetadata metadata = new TableMetadata("my_geopackage", "my_table", 0L);

        // call the updateLastIndexed method to update the lastIndexed field
        boolean success = updateLastIndexed(metadata, System.currentTimeMillis());

        // print the success/failure result
        System.out.println("Update successful? " + success);
    }

    public static boolean updateLastIndexed(TableMetadata metadata, long lastIndexed) {
        boolean updated = metadata.updateLastIndexed(metadata.getGeoPackageId(), metadata.getTableName(), lastIndexed);
        if (updated) {
            metadata.setLastIndexed(lastIndexed);
        }
        return updated;
    }

    @Test
    public void testUpdateLastIndexed() {
        // create a mock TableMetadata object for testing purposes
        TableMetadata metadata = new TableMetadata("my_geopackage", "my_table", 0L);

        // update the lastIndexed field using the method being tested
        boolean success = updateLastIndexed(metadata, System.currentTimeMillis());

        // assert that the method returns true (indicating the update was successful)
        assertTrue(success);

        // assert that the lastIndexed field of the metadata object was updated correctly
        assertEquals(metadata.getLastIndexed(), System.currentTimeMillis());
    }

    @Test
    public void testUpdateLastIndexedFail() {
        // create a mock TableMetadata object for testing purposes
        TableMetadata metadata = new TableMetadata("my_geopackage", "my_table", 0L);

        // update the lastIndexed field using a fake geoPackageId value to force a failure
        boolean success = metadata.updateLastIndexed("fake_id", metadata.getTableName(), System.currentTimeMillis());

        // attempt to update the lastIndexed field again using the method being tested
        success = updateLastIndexed(metadata, System.currentTimeMillis());

        // assert that the method returns false (indicating the update failed)
        assertFalse(success);

        // assert that the lastIndexed field of the metadata object was not updated
        assertEquals(metadata.getLastIndexed(), 0L);
    }

    @Test
    public void testUpdateLastIndexedNoMetadata() {
        // create a null TableMetadata object for testing purposes
        TableMetadata metadata = null;

        // attempt to update the lastIndexed field using the method being tested
        boolean success = updateLastIndexed(metadata, System.currentTimeMillis());

        // assert that the method returns false (indicating the update failed)
        assertFalse(success);
    }
}