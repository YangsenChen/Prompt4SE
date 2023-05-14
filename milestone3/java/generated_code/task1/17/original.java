public boolean updateLastIndexed(TableMetadata metadata, long lastIndexed) {
        boolean updated = updateLastIndexed(metadata.getGeoPackageId(), metadata.getTableName(), lastIndexed);
        if (updated) {
            metadata.setLastIndexed(lastIndexed);
        }
        return updated;
    }