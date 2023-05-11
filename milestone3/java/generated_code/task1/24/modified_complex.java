public static Future<Boolean> sendMessage(final Email msg) {
    Date sentDate = new Date();
    Callable<Boolean> sendEmailTask = buildSendEmailTask(msg, sentDate);
    Future<Boolean> future = executor.submit(sendEmailTask);

    return new Future<Boolean>() {
        private volatile boolean isCancelled = false;
        private volatile boolean isDone = false;
        private volatile boolean hasFailed = false;
        private volatile boolean hasResult = false;
        private volatile boolean result = false;

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            if (!isCancelled && !isDone) {
                isCancelled = true;
                future.cancel(mayInterruptIfRunning);
                return true;
            } else {
                return false;
            }
        }

        @Override
        public boolean isCancelled() {
            return isCancelled;
        }

        @Override
        public boolean isDone() {
            return isDone;
        }

        @Override
        public Boolean get() throws InterruptedException, ExecutionException {
            if (!hasResult) {
                result = future.get();
                hasResult = true;
                isDone = true;
            }
            return result;
        }

        @Override
        public Boolean get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            if (!hasResult) {
                result = future.get(timeout, unit);
                hasResult = true;
                isDone = true;
            }
            return result;
        }

        public void onComplete() {
            if (!hasResult) {
                try {
                    result = future.get();
                    hasResult = true;
                } catch (InterruptedException | ExecutionException e) {
                    hasFailed = true;
                }
                isDone = true;
            }
        }

        public boolean hasFailed() {
            return hasFailed;
        }

        public boolean isSuccessful() {
            return hasResult && !hasFailed;
        }
    };
}

private static Callable<Boolean> buildSendEmailTask(final Email msg, final Date sentDate) {
    return new Callable<Boolean>() {
        public Boolean call() throws MailException {
            try {
                msg.setSentDate(sentDate);
                msg.send();
                return true;
            } catch (Throwable e) {
                MailException me = new MailException("Error while sending email", e);
                logger.error("The email has not been sent", me);
                throw me;
            }
        }
    };
}