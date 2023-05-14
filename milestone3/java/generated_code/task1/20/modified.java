private int indexRows(long geoPackageId, FeatureCursor cursor) {

        int count = -1;

        if (cursor.moveToNext() && (progress == null || progress.isActive())) {

            do {
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
                    Log.e(FeatureIndexer.class.getSimpleName(), "Failed to index feature. " 
                          + "Table: "+ featureDao.getTableName() 
                          + ", Position: " + cursor.getPosition(), e);
                }
            } while ((progress == null || progress.isActive()) && cursor.moveToNext());
            
        }
        
        cursor.close();

        return count;
}