import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

public class Main {

    private static boolean asynchronousSend = true;
    private static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        // create a new Email object and set its properties
        Email email = new Email();
        email.setSubject("My Subject");
        email.setBody("Hello World");
        email.setRecipient("john.doe@example.com");
        email.setSender("alice@example.com");

        // send the email and get a Future object representing the result
        Future<Boolean> future = sendMessage(email);

        // wait for the email to be sent asynchronously (if applicable)
        if (asynchronousSend) {
            try {
                Boolean result = future.get();
                System.out.println("Email sent: " + result);
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Failed to send email: " + e.getMessage());
            }
        } else {
            try {
                Boolean result = future.get(1, TimeUnit.SECONDS);
                System.out.println("Email sent: " + result);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                System.err.println("Failed to send email: " + e.getMessage());
            }
        }

        // shut down the executor
        executor.shutdown();
    }

    public static Future<Boolean> sendMessage(Email msg) {
        // unchanged code from the original snippet
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
                public Boolean get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                    return result.length() == 0;
                }
            };
        }
    }

    @Test
    public void testSendMessageAsynchronous() throws Exception {
        // Arrange
        asynchronousSend = true;
        Email email = new Email();
        email.setSubject("My Subject");
        email.setBody("Hello World");
        email.setRecipient("john.doe@example.com");
        email.setSender("alice@example.com");

        // Act
        Future<Boolean> future = sendMessage(email);
        Boolean result = future.get();

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void testSendMessageSynchronous() throws Exception {
        // Arrange
        asynchronousSend = false;
        Email email = new Email();
        email.setSubject("My Subject");
        email.setBody("Hello World");
        email.setRecipient("john.doe@example.com");
        email.setSender("alice@example.com");

        // Act
        Future<Boolean> future = sendMessage(email);
        Boolean result = future.get();

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void testSendMessageError() throws Exception {
        // Arrange
        asynchronousSend = false;
        Email email = new Email();
        email.setSubject("My Subject");
        email.setBody("Hello World");
        email.setRecipient("john.doe@example.com");
        email.setSender("invalid_email@example.com");

        // Act
        Future<Boolean> future = sendMessage(email);
        Boolean result = future.get();

        // Assert
        assertEquals(false, result);
    }
}