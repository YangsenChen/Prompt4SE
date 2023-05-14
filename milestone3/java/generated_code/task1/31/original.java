public static <T> CompletableFuture<T> failAfter(final long duration) {
		final CompletableFuture<T> promise = new CompletableFuture<>();
		SCHEDULER.schedule(() -> {
			final TimeoutException ex = new TimeoutException("Timeout after " + duration);
			return promise.completeExceptionally(ex);
		}, duration, MILLISECONDS);
		return promise;
	}