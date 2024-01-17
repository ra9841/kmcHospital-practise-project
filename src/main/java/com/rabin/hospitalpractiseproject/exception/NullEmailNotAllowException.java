package com.rabin.hospitalpractiseproject.exception;

public class NullEmailNotAllowException extends RuntimeException{
    public NullEmailNotAllowException(String message) {
        super(message);
    }
}
