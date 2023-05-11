private int indexRows(long geoPackageId, FeatureCursor cursor) {

        int count = -1;

        try {
            while ((progress == null || progress.isActive())
                    && cursor.moveToNext()) {
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