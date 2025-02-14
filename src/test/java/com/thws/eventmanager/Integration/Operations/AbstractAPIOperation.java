package com.thws.eventmanager.Integration.Operations;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thws.eventmanager.infrastructure.GraphQL.Models.GQLModel;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class AbstractAPIOperation {
    protected final HttpClient client = HttpClient.newHttpClient();
    protected final ObjectMapper mapper = new ObjectMapper();
    protected final String baseUrl = "http://localhost:8081/graphql";

    // Generische Methode, die jeden Rückgabetyp (auch Arrays) erlaubt
    protected <R> R executeQuery(String query, String operationName, Class<R> type) throws Exception {
        String requestBody = String.format("{\"query\":\"%s\",\"variables\":{},\"operationName\":\"%s\"}",
                query.replace("\"", "\\\""), operationName);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode root = mapper.readTree(response.body());
        return mapper.treeToValue(root.path("data").path(operationName), type);
    }
} 