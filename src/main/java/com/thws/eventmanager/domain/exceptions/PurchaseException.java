package com.thws.eventmanager.domain.exceptions;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseException extends RuntimeException implements GraphQLError {
    private final String message;
    private final String userId;
    private final String eventId;

    public PurchaseException(String message, String userId, String eventId) {
        super(message);
        this.message = message;
        this.userId = userId;
        this.eventId = eventId;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> extensions = new HashMap<>();
        extensions.put("errorType", "PurchaseException");
        extensions.put("message", message);
        if (userId != null) extensions.put("userId", userId);
        if (eventId != null) extensions.put("eventId", eventId);
        return extensions;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return List.of();
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.PURCHASE_ERROR;
    }

    public enum ErrorType implements ErrorClassification {
        PURCHASE_ERROR;
    }
}
