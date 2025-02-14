package com.thws.eventmanager.Integration.Operations;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
        ObjectNode requestBody = mapper.createObjectNode();
        requestBody.put("query", query);
        requestBody.put("operationName", operationName);
        requestBody.set("variables", mapper.createObjectNode()); // Empty variables

        String requestJson = mapper.writeValueAsString(requestBody); // Convert to JSON
        System.out.println("GraphQL Request: " + requestJson);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestJson))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GraphQL Response: " + response.body());

        JsonNode root = mapper.readTree(response.body());

        if (root.has("errors")) {
            System.err.println("GraphQL Error: " + root.path("errors").toString());
            throw new RuntimeException("GraphQL request failed: " + root.path("errors").toString());
        }

        JsonNode dataNode = root.path("data").path(operationName);
        if (dataNode.isMissingNode() || dataNode.isNull()) {
            throw new RuntimeException("GraphQL request returned null for operation: " + operationName);
        }

        return mapper.treeToValue(dataNode, type);
    }


} 