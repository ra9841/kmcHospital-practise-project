package com.rabin.hospitalpractiseproject.handler;

import com.rabin.hospitalpractiseproject.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerClass {

//    @ExceptionHandler(DoctorEPNumberAlreadyExistException.class)
//    public ResponseEntity<Map<String, String>> exceptionHandlingIfDoctorEPNumberAlreadyExist(DoctorEPNumberAlreadyExistException ex) {
//        Map<String, String> errorMap = new HashMap<>();
//        errorMap.put("message", ex.getMessage());
//        errorMap.put("status code", HttpStatus.ALREADY_REPORTED.toString());
//        return ResponseEntity.ok(errorMap);
//    }

    @ExceptionHandler(DoctorEPNumberAlreadyExistException.class)
    public ProblemDetail exceptionHandlingIfDoctorEPNumberAlreadyExist(DoctorEPNumberAlreadyExistException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.ALREADY_REPORTED,ex.getMessage());
    }

    @ExceptionHandler(DoctorEmailAlreadyExistException.class)
    public ProblemDetail exceptionHandlingIfDoctorEmailAlreadyExist(DoctorEmailAlreadyExistException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.ALREADY_REPORTED,ex.getMessage());
    }

    @ExceptionHandler(DoctorEpNumberDoesNotMatch.class)
    public ResponseEntity<Map<String, String>> exceptionHandlingIfDoctorEpNumberDoesNotMatch(DoctorEpNumberDoesNotMatch ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        errorMap.put("status code", HttpStatus.NOT_FOUND.toString());
        return ResponseEntity.ok(errorMap);
    }

    @ExceptionHandler(HospitalNameNotFoundException.class)
    public ResponseEntity<Map<String, String>> exceptionHandlingIfHospitalNameNotFoundException(HospitalNameNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        errorMap.put("status code", HttpStatus.NOT_FOUND.toString());
        return ResponseEntity.ok(errorMap);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<Map<String, String>> exceptionHandlingIfInternalServerError(InternalServerError ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        errorMap.put("status code", HttpStatus.NOT_FOUND.toString());
        return ResponseEntity.ok(errorMap);
    }

    @ExceptionHandler(hosptalNameIsAlreadyPresent.class)
    public ResponseEntity<Map<String, String>> exceptionHandlingIfHospitalNameIsAlreadyPresent(hosptalNameIsAlreadyPresent ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        errorMap.put("status code", HttpStatus.NOT_FOUND.toString());
        return ResponseEntity.ok(errorMap);
    }

    @ExceptionHandler(PatientAlreadyExistException.class)
    public ProblemDetail exceptionHandlingIfPatientAlreadyExistException(PatientAlreadyExistException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.ALREADY_REPORTED,ex.getMessage());
    }

    @ExceptionHandler(SingleMethodForAllException.class)
    public ProblemDetail exceptionHandlingIfSingleMethodForAllException(SingleMethodForAllException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    errorMap.put(error.getField(), error.getDefaultMessage());
                });
        return errorMap;
    }


}
