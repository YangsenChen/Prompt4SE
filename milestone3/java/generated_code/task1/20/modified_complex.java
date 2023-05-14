private int indexRows(long geoPackageId, FeatureCursor cursor, boolean indexWithProgress) {

    int count = 0;

    if (cursor == null || cursor.getCount() < 1) {
        return count;
    }

    try {
        if (cursor.moveToFirst()) {

            do {
                if ((progress == null || progress.isActive()) || !indexWithProgress) {
                    FeatureRow row = cursor.getRow();

                    if (row.isValid()) {
                        boolean indexed = index(geoPackageId, row, false);

                        if (indexed) {
                            count++;
                        }

                        // Update Progress
                        if (indexWithProgress && progress != null) {
                            progress.addProgress(1);
                        }
                    }
                } else {
                    // Progress is cancelled
                    break;  
                }

            } while (cursor.moveToNext());

        }
    } catch (Exception e) {
        Log.e(FeatureIndexer.class.getSimpleName(), "Failed to index feature.", e);
    } finally {
        cursor.close();
    }

    return count;
}