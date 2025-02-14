package com.thws.eventmanager.Integration.GraphQLQueries;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.AddressInput;
import com.thws.eventmanager.infrastructure.GraphQL.ServerGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Servlet;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class AddressMutationResolverTest {
    //ServerGQL should be started first
    private final String endpoint = "http://localhost:8080/graphql";
    private static Thread serverThread;

    private String extractIdFromResponse(String jsonResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        return rootNode.path("data").path("createAddress").path("id").asText();
    }
    @BeforeAll
    static void startServer() throws Exception {
        serverThread = new Thread(() -> {
            try {
                ServerGQL.main(new String[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverThread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Waiting for server to start...");
        }
    }
    @AfterAll
    static void stopServer() {
       serverThread.interrupt();
       System.out.println("Jetty stopped");
    }

    @Test
    void testAddressCreate_Update_Delete() throws Exception {
        String createMutation = "{ \"query\": \"mutation { createAddress(input: " +
                                "{ street: \\\"old street\\\", city: \\\"old city\\\", zipCode: 12345, " +
                                "country: \\\"old country\\\", no: 10, id: \\\"-1\\\" }) " +
                                "{ id street city zipCode country no } }\" }";

        URL createURL = new URL(endpoint);
        HttpURLConnection createConnection = (HttpURLConnection) createURL.openConnection();
        createConnection.setRequestMethod("POST");
        createConnection.setRequestProperty("Content-Type", "application/json");
        createConnection.setDoOutput(true);

        try (OutputStream os = createConnection.getOutputStream()) {
            os.write(createMutation.getBytes());
            os.flush();
        }
        int responseCode = createConnection.getResponseCode();
        assertEquals(200, responseCode);
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(createConnection.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }
        String jsonCreateResponse = response.toString();
        assertTrue(jsonCreateResponse.contains("\"street\":\"old street\""));
        assertTrue(jsonCreateResponse.contains("\"city\":\"old city\""));

        String addressId = extractIdFromResponse(jsonCreateResponse);

        String updateMutation = "{ \"query\": \"mutation { updateAddress(id: \\\"" + addressId +
                                "\\\", input: { street: \\\"new street\\\", city: \\\"new city\\\", " +
                                "zipCode: 54321, country: \\\"new country\\\", no: 99 }) " +
                                "{ id street city zipCode country no } }\" }";

        URL updateURL = new URL(endpoint);
        HttpURLConnection updateConnection = (HttpURLConnection) updateURL.openConnection();
        updateConnection.setRequestMethod("POST");
        updateConnection.setRequestProperty("Content-Type", "application/json");
        updateConnection.setDoOutput(true);

        try (OutputStream os = updateConnection.getOutputStream()) {
            os.write(updateMutation.getBytes());
            os.flush();
        }

        int updateResponseCode = updateConnection.getResponseCode();
        assertEquals(200, updateResponseCode);

        StringBuilder updateResponse = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(updateConnection.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                updateResponse.append(line);
            }
        }
        String jsonUpdateResponse = updateResponse.toString();
        assertTrue(jsonUpdateResponse.contains("\"street\":\"new street\""));
        assertTrue(jsonUpdateResponse.contains("\"city\":\"new city\""));
        assertTrue(jsonUpdateResponse.contains("\"zipCode\":54321"));
        assertTrue(jsonUpdateResponse.contains("\"country\":\"new country\""));
        assertTrue(jsonUpdateResponse.contains("\"no\":99"));

        String deleteMutation = "{ \"query\": \"mutation { deleteAddress(id: \\\"" + addressId +
                                "\\\") { id street city zipCode country no } }\" }";
        URL deleteURL = new URL(endpoint);
        HttpURLConnection deleteConnection = (HttpURLConnection) deleteURL.openConnection();
        deleteConnection.setRequestMethod("POST");
        deleteConnection.setRequestProperty("Content-Type", "application/json");
        deleteConnection.setDoOutput(true);

        try (OutputStream os = deleteConnection.getOutputStream()) {
            os.write(deleteMutation.getBytes());
            os.flush();
        }
        int deleteResponseCode = deleteConnection.getResponseCode();
        assertEquals(200, deleteResponseCode);

        StringBuilder deleteResponse = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(deleteConnection.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                deleteResponse.append(line);
            }
        }
        String jsonDeleteResponse = deleteResponse.toString();
        assertTrue(jsonDeleteResponse.contains("\"id\":\"" + addressId + "\""));
        assertTrue(jsonDeleteResponse.contains("\"street\":\"new street\""));

    }
}