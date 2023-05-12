package org.example;

import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBException;

/**
 * Unit test for simple App.
 */
public class AppTest {
//    @Test
//    public void testToObject() throws JAXBException {
//        // Arrange
//        String xmlFilePath = "./file.xml";
//        FeedManager feedManager = new FeedManager(xmlFilePath);
//
//        // Act
//        Feed feed = feedManager.toObject();
//
//        // Assert
//        Assert.assertNotNull(feed);
//        Assert.assertEquals("Expected name", feed.getName());
//        Assert.assertEquals("Expected description", feed.getDescription());
//        Assert.assertEquals("Expected version", feed.getVersion());
//    }
//
//    @Test(expected = JAXBException.class)
//    public void testToObject_JAXBException() throws JAXBException {
//        // Arrange
//        String xmlFilePath = "./nonexistent.xml";
//        FeedManager feedManager = new FeedManager(xmlFilePath);
//
//        // Act
//        feedManager.toObject(); // This should throw a JAXBException
//
//        // Assert
//        // The test should fail with the expected exception
//    }

    @Test(expected = RuntimeException.class)
    public void testToObject_RuntimeException() throws JAXBException {
        // Arrange
        String xmlFilePath = "./invalid.xml";
        FeedManager feedManager = new FeedManager(xmlFilePath);

        // Act
        feedManager.toObject(); // This should throw a RuntimeException

        // Assert
        // The test should fail with the expected exception
    }
}
