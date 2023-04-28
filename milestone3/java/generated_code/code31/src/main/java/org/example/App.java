package org.example;

import org.junit.*;

import java.util.concurrent.*;

public class App {

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

//    // chatgpt generated  semantically equivalent code: test pass 1/3
//    // chatgpt made the following change:
//    // This implementation creates a new ScheduledExecutorService with a single thread, and schedules a Runnable that completes the CompletableFuture with a TimeoutException after the specified duration. It returns the CompletableFuture.
//    public static <T> CompletableFuture<T> failAfter(final long duration) {
//        final CompletableFuture<T> promise = new CompletableFuture<>();
//        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        final ScheduledFuture<?> timeout = scheduler.schedule(() -> {
//            final TimeoutException ex = new TimeoutException("Timeout after " + duration);
//            promise.completeExceptionally(ex);
//        }, duration, TimeUnit.MILLISECONDS);
//        promise.whenComplete((result, error) -> timeout.cancel(true));
//        return promise;
//    }

    private static final java.util.concurrent.ScheduledExecutorService SCHEDULER
            = java.util.concurrent.Executors.newScheduledThreadPool(1);


    // Existing code ...
}