package com.packtpub.jdk.core.eleven.chapter10;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.Test;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import static java.lang.System.out;

public class App {


    @Test
    public void howToMakeHttpGetRequest() {

        try {

            final HttpClient client = HttpClient.newBuilder().build();
            final HttpRequest request = HttpRequest.newBuilder(new URI("http://httpbin.org/get"))
                    //.uri()
                    //.timeout(Duration.ofSeconds(2))
                    //.headers()
                    .GET()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            out.println(response.statusCode());
            out.println(response.body());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Test
    public void howToMakeHttpPost() {

        try {

            final ObjectMapper mapper = new ObjectMapper();
            HttpClient client = HttpClient.newBuilder().build();
            Map<String, String> payload = Map.of("key1", "value1", "key2", "value2");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://httpbin.org/post"))
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(payload)))
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            out.println(response.body());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void howToMakeHttpGetWithBasicAuth() {


        @Data
        @AllArgsConstructor
        @EqualsAndHashCode(callSuper = true)
        final class UserNameAndPwd extends Authenticator {

            private final String userName;
            private final String password;

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password.toCharArray());
            }
        }

        try {

            HttpClient client = HttpClient.newBuilder()
                    .authenticator(new UserNameAndPwd("user", "passwd")).build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://httpbin.org/basic-auth/user/passwd"))
                    .GET()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            out.println(response.statusCode());
            out.println(response.body());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    public void howToMakeHttpAsyncRequest() {

        try {

            final HttpClient client = HttpClient.newBuilder().build();
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://httpbin.org/get"))
                    .GET()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();


            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAccept((result) -> {
                        out.println(result.statusCode());
                        out.println(result.body());
                    }).get();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void howToMakeHttpRequestWithUniRest() {
        try {

            out.println(Unirest.get("http://httpbin.org/get").asJson().getBody());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
