package com.thws.eventmanager.domain.services.other;

import com.thws.eventmanager.domain.exceptions.UnauthorizedException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


/*
This is just a demo implementation on how you can authenticate with the THWS API.
Authentication is currently NOT implemented completely (due to error handling and interactions between resolver and client), although you can query authenticate to receive a token.
 */


public class AuthenticationUseCase {
    private static AuthenticationUseCase instance;
    public String token;

    private AuthenticationUseCase() {}

    public static AuthenticationUseCase getInstance() {
        if (instance == null) {
            instance = new AuthenticationUseCase();
        }
        return instance;
    }

    private static String serverURL = "https://staging.api.fiw.thws.de/auth/api/users/me";

    public static String authenticate(String username, String password) throws UnauthorizedException {
        AuthenticationUseCase authenticationUseCase = getInstance();
        try (final HttpClient httpClient = HttpClient.newHttpClient()) {

            String auth = username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(serverURL))
                    .header("Authorization", "Basic " + encodedAuth)
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                authenticationUseCase.token = response.headers().map().get("X-fhws-jwt-token").getFirst();
                return authenticationUseCase.token;
            } else {
                throw new RuntimeException("Authentication failed, Username or Password are not Valid");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new UnauthorizedException("Authentication failed, Username or Password are not Valid", e);
        }
    }

    public boolean isAuthorized() {
        AuthenticationUseCase authenticationUseCase = getInstance();
        if(authenticationUseCase.token == null || authenticationUseCase.token.isEmpty()) return false;

        try (final HttpClient httpClient = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(serverURL))
                    .header("Authorization", "Bearer " + token)
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public String getToken() {
        return token;
    }
}
