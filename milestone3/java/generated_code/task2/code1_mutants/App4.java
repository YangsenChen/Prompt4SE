package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */


interface Converter<S, T> {
    T convert(S source);
}

public class App 
{
    Converter<Map<String, String>, OAuth2Error> errorConverter;

    public App() {
        // Initialize any necessary objects or variables here
    }

    public final void setErrorConverter(Converter<Map<String, String>, OAuth2Error> errorConverter) {
        if(errorConverter == null){
            throw new IllegalArgumentException("errorConverter cannot be null");
        }
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

        App main = new App();

        // Create a sample Converter implementation
        Converter<Map<String, String>, OAuth2Error> converter = map -> {
            return new OAuth2Error(map.get("error"), map.get("error_description"), map.get("error_uri"));
        };

        // Set the error converter

        // Use the error converter to convert a Map to an OAuth2Error object
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", "invalid_request");
        errorMap.put("error_description", "The request is missing a required parameter");
        OAuth2Error error = main.errorConverter.convert(errorMap);
        System.out.println(error);

    }

}
