package com.thws.eventmanager.domain.exceptions;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException(String message){super(message);}

    public InvalidUserException(String message, Throwable cause){super(message, cause);}
}
