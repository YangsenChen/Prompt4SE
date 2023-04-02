import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Main {

    private Converter<Map<String, String>, OAuth2Error> errorConverter;

    public Main() {
        // Initialize any necessary objects or variables here
    }

    public final void setErrorConverter(Converter<Map<String, String>, OAuth2Error> errorConverter) {
        Assert.notNull(errorConverter, "errorConverter cannot be null");
        this.errorConverter = errorConverter;
    }

    // equivalent code
//    public void setErrorConverter(Converter<Map<String, String>, OAuth2Error> errorConverter) {
//        if(errorConverter == null){
//            throw new IllegalArgumentException("errorConverter cannot be null");
//        }
//        this.errorConverter = errorConverter;
//    }

    public static void main(String[] args) {

        Main main = new Main();

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
        System.out.println(error);

    }

    @Test
    public void testErrorConverterNotNull() {
        Main main = new Main();
        try {
            main.setErrorConverter(null);
            Assert.fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("errorConverter cannot be null", e.getMessage());
        }
    }

    @Test
    public void testConvertErrorMap() {
        Main main = new Main();

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
        Main main = new Main();

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

}

class OAuth2Error {
    private String error;
    private String errorDescription;
    private String errorUri;

    public OAuth2Error(String error, String errorDescription, String errorUri) {
        this.error = error;
        this.errorDescription = errorDescription;
        this.errorUri = errorUri;
    }

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public String getErrorUri() {
        return errorUri;
    }

    @Override
    public String toString() {
        return "OAuth2Error{" +
                "error='" + error + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                ", errorUri='" + errorUri + '\'' +
                '}';
    }
}

interface Converter<F, T> extends Function<F, T> {
}