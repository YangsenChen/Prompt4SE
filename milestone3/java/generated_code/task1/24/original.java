public static Future<Boolean> sendMessage(final Email msg) {
        if (asynchronousSend) {
            return executor.submit(new Callable<Boolean>() {

                public Boolean call() {
                    try {
                        msg.setSentDate(new Date());
                        msg.send();
                        return true;
                    } catch (Throwable e) {
                        MailException me = new MailException("Error while sending email", e);
                        logger.error("The email has not been sent", me);
                        return false;
                    }
                }
            });
        } else {
            final StringBuffer result = new StringBuffer();
            try {
                msg.setSentDate(new Date());
                msg.send();
            } catch (Throwable e) {
                MailException me = new MailException("Error while sending email", e);
                logger.error("The email has not been sent", me);
                result.append("oops");
            }
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
                    return result.length() == 0;
                }

                public Boolean get(long timeout, TimeUnit unit)
                        throws InterruptedException, ExecutionException, TimeoutException {
                    return result.length() == 0;
                }
            };
        }
    }