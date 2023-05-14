package org.example;


class TableMetadata {
    private String geoPackageId;
    private String tableName;
    private long lastIndexed;

    public TableMetadata(String geoPackageId, String tableName, long lastIndexed) {
        this.geoPackageId = geoPackageId;
        this.tableName = tableName;
        this.lastIndexed = lastIndexed;
    }

    public String getGeoPackageId() {
        return geoPackageId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setLastIndexed(long lastIndexed) {
        this.lastIndexed = lastIndexed;
    }

    public boolean updateLastIndexed(String geoPackageId, String tableName, long lastIndexed) {
        // Simulating a successful database update operation
        // In a real scenario, this would check if the given geoPackageId and tableName exist, then update the lastIndexed
        return true;
    }
}

public class App {
    public static boolean updateLastIndexed(TableMetadata metadata, long lastIndexed) {
        boolean updated = metadata.updateLastIndexed(metadata.getGeoPackageId(), metadata.getTableName(), lastIndexed);

        return updated;
    }

    public static void main(String[] args) {
        TableMetadata metadata = new TableMetadata("geo1", "table1", 1000);
        boolean isUpdated = updateLastIndexed(metadata, 2000);

        System.out.println("Update operation status: " + (isUpdated ? "Success" : "Failure"));
//        System.out.println("New lastIndexed: " + metadata.getLastIndexed());
    }
}
