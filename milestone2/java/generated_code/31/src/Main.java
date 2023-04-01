import org.junit.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {

    public static void main(String[] args) {
        CompletableFuture<String> future = fetchData();
        CompletableFuture<String> result = failAfter(1000L);
        CompletableFuture<Object> firstCompleted = CompletableFuture.anyOf(future, result);
        try {
            String data = (String) firstCompleted.get();
            System.out.println("Data fetched successfully: " + data);
        } catch (Exception e) {
            if (e.getCause() instanceof TimeoutException) {
                System.out.println("Fetching data timed out");
            } else {
                e.printStackTrace();
            }
        }
    }

    public static CompletableFuture<String> fetchData() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                return "Data from server";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static <T> CompletableFuture<T> failAfter(final long duration) {
        final CompletableFuture<T> promise = new CompletableFuture<>();
        SCHEDULER.schedule(() -> {
            final TimeoutException ex = new TimeoutException("Timeout after " + duration);
            return promise.completeExceptionally(ex);
        }, duration, TimeUnit.MILLISECONDS);
        return promise;
    }

    private static final java.util.concurrent.ScheduledExecutorService SCHEDULER
            = java.util.concurrent.Executors.newScheduledThreadPool(1);

    @Test
    public void testFetchDataSuccess() throws Exception {
        CompletableFuture<String> future = fetchData();
        String expected = "Data from server";
        String actual = future.get();
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = TimeoutException.class)
    public void testFetchDataTimeout() throws Exception {
        CompletableFuture<String> future = fetchData();
        CompletableFuture<String> result = failAfter(1000L);
        CompletableFuture<Object> firstCompleted = CompletableFuture.anyOf(future, result);
        firstCompleted.get();
    }

    @Test(expected = RuntimeException.class)
    public void testFetchDataException() throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Data fetch failed");
        });
        future.get();
    }

    // Existing code ...
}