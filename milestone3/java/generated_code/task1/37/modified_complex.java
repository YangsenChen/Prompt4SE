private void trackOrInsertDelayedLocatorIfNecessary(IMetric metric, MutationBatch mutationBatch, Clock clock) throws DatabaseException {
    // Get the locator and collection time from the metric
    Locator locator = metric.getLocator();
    long collectionTime = metric.getCollectionTime();

    // Calculate the delay between the collection time and the current time
    long delay = clock.now().getMillis() - collectionTime;

    // Determine the maximum age allowed for the metric based on its type
    long maxAgeAllowed = getMaxAgeAllowedForMetricType(metric);

    // Check if the metric is delayed according to the current time and its maximum age allowed
    boolean isDelayed = (delay > maxAgeAllowed);

    // If the metric is delayed, track or insert its delayed locator
    if (isDelayed) {
        // Determine the granularity slot for the delayed locator
        int slot = getGranularitySlotForMetric(metric);

        // Get the existing delayed locators for the granularity slot
        List<Locator> delayedLocators = getExistingDelayedLocatorsForSlot(slot);

        // Check if the locator is already in the delayed locators
        boolean isLocatorAlreadyDelayed = delayedLocators.contains(locator);

        if (!isLocatorAlreadyDelayed) {
            // Get the minimum collection time for all of the delayed locators for the slot
            long minCollectionTime = getMinCollectionTimeForDelayedLocators(slot);

            // Check if the metric's collection time is earlier than the minimum collection time
            boolean isEarlierThanOtherDelayedMetrics = (collectionTime < minCollectionTime);

            if (isEarlierThanOtherDelayedMetrics) {
                // Replace all older delayed locators for the granularity slot with the current delayed locator
                replaceOlderDelayedLocatorsWithCurrent(locator, slot, mutationBatch);
            } else {
                // Insert the delayed locator into the mutation batch
                insertDelayedLocatorIntoMutationBatch(DELAYED_METRICS_STORAGE_GRANULARITY, slot, locator, mutationBatch);
            }

            // Update the cache to reflect that the locator has been tracked for the slot
            LocatorCache.getInstance().setDelayedLocatorForASlotCurrent(slot, locator);
        }
    }
}