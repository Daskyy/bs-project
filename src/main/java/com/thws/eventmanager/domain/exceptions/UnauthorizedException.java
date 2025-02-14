package com.thws.eventmanager.domain.exceptions;

public class UnauthorizedException extends RuntimeException {
    private final String errorCode;
    private final String userMessage;

    public UnauthorizedException() {
        this("Access denied. Please log in.");
    }

    public UnauthorizedException(String message) {
        super(message);
        this.errorCode = "UNAUTHORIZED";
        this.userMessage = "Access denied. Please log in.";
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "UNAUTHORIZED";
        this.userMessage = "Access denied. Please log in.";
    }

    public UnauthorizedException(String message, String errorCode, String userMessage) {
        super(message);
        this.errorCode = errorCode;
        this.userMessage = userMessage;
    }
}
