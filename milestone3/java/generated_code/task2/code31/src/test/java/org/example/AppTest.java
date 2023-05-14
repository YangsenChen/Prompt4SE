package org.example;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

import static org.example.App.failAfter;
import static org.example.App.fetchData;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    @org.junit.Test
    public void testFetchDataSuccess() throws Exception {
        CompletableFuture<String> future = fetchData();
        String expected = "Data from server";
        String actual = future.get();
        Assert.assertEquals(expected, actual);
    }

//    @org.junit.Test(expected = TimeoutException.class)
//    public void testFetchDataTimeout() throws Exception {
//        CompletableFuture<String> future = fetchData();
//        CompletableFuture<String> result = failAfter(1000L);
//        CompletableFuture<Object> firstCompleted = CompletableFuture.anyOf(future, result);
//        firstCompleted.get();
//    }

//    @Test(expected = RuntimeException.class)
//    public void testFetchDataException() throws Exception {
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            throw new RuntimeException("Data fetch failed");
//        });
//        future.get();
//    }

}
