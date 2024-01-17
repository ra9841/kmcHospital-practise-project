package com.rabin.hospitalpractiseproject.exception;

public class PatientAlreadyExistException extends RuntimeException{
    public PatientAlreadyExistException(String message) {
        super(message);
    }
}
