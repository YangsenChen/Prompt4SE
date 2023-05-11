public static Future<Boolean> sendMessage(final Email msg) {
    Future<Boolean> future = null;
    Date sentDate = new Date();

    if (asynchronousSend) {
        Callable<Boolean> sendEmailTask = buildSendEmailTask(msg, sentDate);
        future = executor.submit(sendEmailTask);
    } else {
        try {
            msg.setSentDate(sentDate);
            msg.send();
            future = buildFuture(true);
        } catch (Throwable e) {
            MailException me = new MailException("Error while sending email", e);
            logger.error("The email has not been sent", me);
            future = buildFuture(false);
        }
    }
    return future;
}

private static Callable<Boolean> buildSendEmailTask(final Email msg, final Date sentDate) {
    Callable<Boolean> sendEmailTask = new Callable<Boolean>() {
        public Boolean call() throws MailException {
            try {
                msg.setSentDate(sentDate);
                msg.send();
                return true;
            } catch (Throwable e) {
                MailException me = new MailException("Error while sending email", e);
                logger.error("The email has not been sent", me);
                return false;
            }
        }
    };
    return sendEmailTask;
}

private static Future<Boolean> buildFuture(final boolean isSuccessful) {
    return new Future<Boolean>() {
        public boolean cancel(boolean mayInterruptIfRunning) {
            return false;
        }

        public boolean isCancelled() {
            return false;
        }

        public boolean isDone() {
            return true;
        }

        public Boolean get() throws InterruptedException, ExecutionException {
            return isSuccessful;
        }

        public Boolean get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return isSuccessful;
        }
    };
}