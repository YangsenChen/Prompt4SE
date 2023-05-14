public static <T> CompletableFuture<T> failAfter(final long duration) {
		final CompletableFuture<T> promise = new CompletableFuture<>();
		final Thread thread = new Thread(() -> {
			try {
				Thread.sleep(duration);
			} catch (InterruptedException e) {
				return; // timeout was cancelled
			}
			final TimeoutException ex = new TimeoutException("Timeout after " + duration);
			promise.completeExceptionally(ex);
		});
		thread.start();
		promise.whenComplete((result, exception) -> {
			if (!thread.isInterrupted()) {
				thread.interrupt(); // cancel the timeout if promise is completed
			}
		});
		return promise;
	}