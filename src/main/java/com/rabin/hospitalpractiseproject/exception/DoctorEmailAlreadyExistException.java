package com.rabin.hospitalpractiseproject.exception;

public class DoctorEmailAlreadyExistException extends RuntimeException{
    public DoctorEmailAlreadyExistException(String message) {
        super(message);
    }
}
