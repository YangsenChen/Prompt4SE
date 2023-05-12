package org.example;

/**
 * Hello world!
 *
 */
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

public class App {

    public static ThreadFactory daemonThreadFactory(final String name, final int priority) {
        return new ThreadFactory() {
            private AtomicInteger count = new AtomicInteger();

            @Override
            public Thread newThread(final Runnable r) {
                Thread thread = new Thread(r);
                thread.setName(name + '-' + count.incrementAndGet());
                thread.setDaemon(true);
                thread.setPriority(priority);
                return null;
            }
        };
    }
//    // chatgpt generated semantically equivalent code: test pass 3/3
//    // chatgpt answer on the changes made: I replaced the anonymous class with a lambda expression to define the ThreadFactory instance. I also moved the AtomicInteger instance inside the lambda expression since it's only used by the newThread method.
//    public static ThreadFactory daemonThreadFactory(final String name, final int priority) {
//        AtomicInteger count = new AtomicInteger();
//
//        return (Runnable r) -> {
//            Thread thread = new Thread(r);
//            thread.setName(name + '-' + count.incrementAndGet());
//            thread.setDaemon(true);
//            thread.setPriority(priority);
//            return thread;
//        };
//    }
    public static void main(String[] args) {
        ThreadFactory daemonThreadFactory = daemonThreadFactory("BackgroundThread", Thread.MIN_PRIORITY);

        for (int i = 0; i < 5; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Task running in " + Thread.currentThread().getName());
                }
            };

            Thread t = daemonThreadFactory.newThread(task);
            t.start();
        }
    }
}