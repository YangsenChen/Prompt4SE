private void insertLocatorIfDelayed(IMetric metric, MutationBatch mutationBatch, Clock clock) {
        Locator locator = metric.getLocator();

        long delay = clock.now().getMillis() - metric.getCollectionTime();
        if (delay > MAX_AGE_ALLOWED) {

            //track locator for configured granularity level. to re-roll only the delayed locator's for that slot
            int slot = DELAYED_METRICS_STORAGE_GRANULARITY.slot(metric.getCollectionTime());
            if (!LocatorCache.getInstance().isDelayedLocatorForASlotCurrent(slot, locator)) {
                insertDelayedLocator(DELAYED_METRICS_STORAGE_GRANULARITY, slot, locator, mutationBatch);
                LocatorCache.getInstance().setDelayedLocatorForASlotCurrent(slot, locator);
            }
        }
    }