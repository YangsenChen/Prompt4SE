package org.example;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ThreadFactory;

import static org.example.App.daemonThreadFactory;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    @org.junit.Test
    public void testDaemonThreadFactoryName() {
        ThreadFactory daemonThreadFactory = daemonThreadFactory("TestThread", Thread.MIN_PRIORITY);
        Thread t = daemonThreadFactory.newThread(() -> {});
        Assert.assertEquals("TestThread-1", t.getName());
    }

    @org.junit.Test
    public void testDaemonThreadFactoryPriority() {
        ThreadFactory daemonThreadFactory = daemonThreadFactory("TestThread", Thread.MAX_PRIORITY);
        Thread t = daemonThreadFactory.newThread(() -> {});
        Assert.assertEquals(Thread.MAX_PRIORITY, t.getPriority());
    }

    @Test
    public void testDaemonThreadFactoryDaemon() {
        ThreadFactory daemonThreadFactory = daemonThreadFactory("TestThread", Thread.NORM_PRIORITY);
        Thread t = daemonThreadFactory.newThread(() -> {});
        Assert.assertTrue(t.isDaemon());
    }
}
