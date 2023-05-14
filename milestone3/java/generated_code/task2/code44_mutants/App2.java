package org.example;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;

public class App{
    private String serviceTicket;

    public User createUser(User userParam) {
        if (userParam != null && this.serviceTicket == null) {
            userParam.setServiceTicket(this.serviceTicket);
        }

        return new User(this.putJson(userParam, WS.Path.User.Version1.userCreate()));
    }

    String putJson(User user, String url) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(user);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        User userParam = new User("{\"serviceTicket\":\"testTicket\"}"); // assuming JSON string
        App main = new App();
//        main.setServiceTicket("testTicket");
        User newUser = main.createUser(userParam);

        // print new user's service ticket
//        System.out.println(newUser.getServiceTicket());
    }
}

class User {
    private String serviceTicket;
    // other fields...

    public User(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(json, User.class);

            this.serviceTicket = user.serviceTicket;
            // copy other fields...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setServiceTicket(String serviceTicket) {
        this.serviceTicket = serviceTicket;
    }


    public String getServiceTicket() {
        return this.serviceTicket;
    }
}

class WS {
    static class Path {
        static class User {
            static class Version1 {
                static String userCreate() {
                    return "http://example.com/api/v1/userCreate";  // replace with your actual URL
                }
            }
        }
    }
}
