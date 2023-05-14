public static <T, R> CompletableFuture<R> failAfter(final long duration, final Function<TimeoutException, R> recoveryFunction) {
    // Create a new CompletableFuture to represent the overall operation
    final CompletableFuture<R> resultPromise = new CompletableFuture<>();
    
    // Create a separate CompletableFuture to represent the timeout
    final CompletableFuture<Void> timeoutPromise = new CompletableFuture<>();
    
    // Create a task to complete the timeoutPromise after the specified duration
    final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    final ScheduledFuture<?> timeoutFuture = executor.schedule(() -> timeoutPromise.complete(null), duration, TimeUnit.MILLISECONDS);
    
    // Create a task to complete the resultPromise if the operation completes before the timeout
    final CompletableFuture<T> operationPromise = new CompletableFuture<>();
    operationPromise.whenComplete((result, exception) -> {
        if (!timeoutPromise.isDone()) {
            timeoutFuture.cancel(false); // cancel the timeout task
            if (exception != null) {
                resultPromise.completeExceptionally(exception);
            } else {
                try {
                    final R recoveryResult = recoveryFunction.apply(null);
                    resultPromise.complete(recoveryResult);
                } catch (Exception e) {
                    resultPromise.completeExceptionally(e);
                }
            }
        }
    });
    
    // Combine the operationPromise and the timeoutPromise into a single "race" operation
    final CompletableFuture<Void> racePromise = operationPromise.applyToEither(timeoutPromise, Function.identity());
    
    // Add a callback to the racePromise that completes the resultPromise if the timeout expires
    racePromise.whenComplete((result, exception) -> {
        if (exception instanceof CompletionException && exception.getCause() instanceof TimeoutException) {
            try {
                final R recoveryResult = recoveryFunction.apply((TimeoutException) exception.getCause());
                resultPromise.complete(recoveryResult);
            } catch (Exception e) {
                resultPromise.completeExceptionally(e);
            }
        }
    });
    
    // Return the overall resultPromise to the caller
    return resultPromise;
}