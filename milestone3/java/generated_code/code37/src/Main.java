import org.testng.annotations.Test;

import java.util.Random;

public class Main {
    private static final long MAX_AGE_ALLOWED = 1000; // max age allowed for a metric in milliseconds
    private static final Granularity DELAYED_METRICS_STORAGE_GRANULARITY = Granularity.SECOND;

    public static void main(String[] args) {
        // Run the tests below
    }

    @Test
    public void testInsertLocatorIfDelayedWithFreshMetric() {
        Clock clock = new SystemClock();
        MutationBatch mutationBatch = new MutationBatch();
        IMetric metric = generateRandomMetric();
        metric.setCollectionTime(clock.now().minusSeconds(1).getMillis());
        insertLocatorIfDelayed(metric, mutationBatch, clock);

        // Assert that no metrics were inserted into MutationBatch, since the metric is fresh
        assertEquals(0, mutationBatch.size());
    }

    @Test
    public void testInsertLocatorIfDelayedWithDelayedMetric() {
        Clock clock = new SystemClock();
        MutationBatch mutationBatch = new MutationBatch();
        IMetric metric = generateRandomMetric();
        metric.setCollectionTime(clock.now().minusMillis(MAX_AGE_ALLOWED + 1).getMillis());
        insertLocatorIfDelayed(metric, mutationBatch, clock);

        // Assert that one metric was inserted into MutationBatch
        assertEquals(1, mutationBatch.size());

        Mutation mutation = mutationBatch.iterator().next();
        byte[] rowKey = mutation.getRow();
        byte[] columnQualifier = mutation.getColumns().iterator().next().getQualifier();

        // Assert that the mutation corresponds to the expected row key and column qualifier
        // (Assuming that the insertDelayedLocator method generates the same row key and column qualifier format)
        String expectedRowKey = String.format("%d:%s", DELAYED_METRICS_STORAGE_GRANULARITY.slot(metric.getCollectionTime()), metric.getLocator().toString());
        String expectedColumnQualifier = String.valueOf(metric.getCollectionTime());
        assertArrayEquals(expectedRowKey.getBytes(), rowKey);
        assertArrayEquals(expectedColumnQualifier.getBytes(), columnQualifier);
    }

    @Test
    public void testInsertLocatorIfDelayedWithDuplicateDelayedMetric() {
        Clock clock = new SystemClock();
        MutationBatch mutationBatch = new MutationBatch();
        IMetric metric = generateRandomMetric();
        metric.setCollectionTime(clock.now().minusMillis(MAX_AGE_ALLOWED + 1).getMillis());
        insertLocatorIfDelayed(metric, mutationBatch, clock);
        insertLocatorIfDelayed(metric, mutationBatch, clock);

        // Assert that there is only one mutation in MutationBatch
        assertEquals(1, mutationBatch.size());
    }

    private static void insertLocatorIfDelayed(IMetric metric, MutationBatch mutationBatch, Clock clock) {
        Locator locator = metric.getLocator();

        long delay = clock.now().getMillis() - metric.getCollectionTime();
        if (delay > MAX_AGE_ALLOWED) {
            int slot = DELAYED_METRICS_STORAGE_GRANULARITY.slot(metric.getCollectionTime());
            if (!LocatorCache.getInstance().isDelayedLocatorForASlotCurrent(slot, locator)) {
                insertDelayedLocator(DELAYED_METRICS_STORAGE_GRANULARITY, slot, locator, mutationBatch);
                LocatorCache.getInstance().setDelayedLocatorForASlotCurrent(slot, locator);
            }
        }
    }

    private static IMetric generateRandomMetric() {
        Random random = new Random();
        long collectionTime = System.currentTimeMillis() - random.nextInt(2000);
        Locator locator = new Locator("namespace", "metric", "tag");
        return new Metric(locator, collectionTime, random.nextDouble());
    }
}