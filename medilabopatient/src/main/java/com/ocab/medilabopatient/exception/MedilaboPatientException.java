package com.ocab.medilabopatient.exception;

import org.springframework.http.HttpStatus;

public class MedilaboPatientException extends RuntimeException {
    private final HttpStatus status;

    public MedilaboPatientException(String errorMessage, HttpStatus status) {
        super(errorMessage);
        this.status = status;
    }


    public HttpStatus getStatus() {
        return status;
    }
}
