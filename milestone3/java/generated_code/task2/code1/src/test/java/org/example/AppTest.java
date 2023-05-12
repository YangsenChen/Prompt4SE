package org.example;


import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void testErrorConverterNotNull() {
        App main = new App();
        try {
            main.setErrorConverter(null);
            Assert.fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("errorConverter cannot be null", e.getMessage());
        }
    }

    @Test
    public void testConvertErrorMap() {
        App main = new App();

        // Create a sample Converter implementation
        Converter<Map<String, String>, OAuth2Error> converter = map -> {
            return new OAuth2Error(map.get("error"), map.get("error_description"), map.get("error_uri"));
        };

        // Set the error converter
        main.setErrorConverter(converter);

        // Use the error converter to convert a Map to an OAuth2Error object
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", "invalid_request");
        errorMap.put("error_description", "The request is missing a required parameter");
        OAuth2Error error = main.errorConverter.convert(errorMap);

        Assert.assertEquals("invalid_request", error.getError());
        Assert.assertEquals("The request is missing a required parameter", error.getErrorDescription());
        Assert.assertNull(error.getErrorUri());
    }

    @Test
    public void testConvertErrorMapMissingError() {
        App main = new App();

        // Create a sample Converter implementation
        Converter<Map<String, String>, OAuth2Error> converter = map -> {
            return new OAuth2Error(map.get("error"), map.get("error_description"), map.get("error_uri"));
        };

        // Set the error converter
        main.setErrorConverter(converter);

        // Use the error converter to convert a Map to an OAuth2Error object
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error_description", "The request is missing a required parameter");
        OAuth2Error error = main.errorConverter.convert(errorMap);

        Assert.assertNull(error.getError());
        Assert.assertEquals("The request is missing a required parameter", error.getErrorDescription());
        Assert.assertNull(error.getErrorUri());
    }

    @Test
    public void testErrorConverterNotNull1() {
        App main = new App();
        try {
            main.setErrorConverter1(null);
            Assert.fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("errorConverter cannot be null", e.getMessage());
        }
    }

    @Test
    public void testConvertErrorMap1() {
        App main = new App();

        // Create a sample Converter implementation
        Converter<Map<String, String>, OAuth2Error> converter = map -> {
            return new OAuth2Error(map.get("error"), map.get("error_description"), map.get("error_uri"));
        };

        // Set the error converter
        main.setErrorConverter1(converter);

        // Use the error converter to convert a Map to an OAuth2Error object
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", "invalid_request");
        errorMap.put("error_description", "The request is missing a required parameter");
        OAuth2Error error = main.errorConverter.convert(errorMap);

        Assert.assertEquals("invalid_request", error.getError());
        Assert.assertEquals("The request is missing a required parameter", error.getErrorDescription());
        Assert.assertNull(error.getErrorUri());
    }

    @Test
    public void testConvertErrorMapMissingError1() {
        App main = new App();

        // Create a sample Converter implementation
        Converter<Map<String, String>, OAuth2Error> converter = map -> {
            return new OAuth2Error(map.get("error"), map.get("error_description"), map.get("error_uri"));
        };

        // Set the error converter
        main.setErrorConverter1(converter);

        // Use the error converter to convert a Map to an OAuth2Error object
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error_description", "The request is missing a required parameter");
        OAuth2Error error = main.errorConverter.convert(errorMap);

        Assert.assertNull(error.getError());
        Assert.assertEquals("The request is missing a required parameter", error.getErrorDescription());
        Assert.assertNull(error.getErrorUri());
    }
}
