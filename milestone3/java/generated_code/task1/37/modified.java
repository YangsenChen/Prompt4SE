private void insertLocatorIfDelayed(IMetric metric, MutationBatch mutationBatch, Clock clock) {
    // Extract the necessary data from the metric
    Locator locator = metric.getLocator();
    long collectionTime = metric.getCollectionTime();

    // Calculate the delay between the collection time and the current time
    long delay = clock.now().getMillis() - collectionTime;

    // Determine if the delay exceeds the maximum age allowed for the metric
    boolean isDelayed = (delay > MAX_AGE_ALLOWED);

    if (isDelayed) {
        // Determine the granularity slot for the delayed locator
        int slot = DELAYED_METRICS_STORAGE_GRANULARITY.slot(collectionTime);

        // Check if the delayed locator has already been tracked for the slot
        boolean isLocatorDelayed = LocatorCache.getInstance().isDelayedLocatorForASlotCurrent(slot, locator);

        if (!isLocatorDelayed) {
            // Track the delayed locator for the granularity slot
            insertDelayedLocator(DELAYED_METRICS_STORAGE_GRANULARITY, slot, locator, mutationBatch);

            // Update the cache to reflect that the locator has been tracked for the slot
            LocatorCache.getInstance().setDelayedLocatorForASlotCurrent(slot, locator);
        }
    }
}