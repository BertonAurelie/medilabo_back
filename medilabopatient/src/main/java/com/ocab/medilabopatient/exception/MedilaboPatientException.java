package com.ocab.medilabopatient.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom exception for the Medilabo Patient application.
 */
public class MedilaboPatientException extends RuntimeException {

    /**
     * HTTP status associated with the exception.
     */
    private final HttpStatus status;

    /**
     * Constructor for MedilaboPatientException.
     *
     * @param errorMessage exception message
     * @param status HTTP status
     */
    public MedilaboPatientException(String errorMessage, HttpStatus status) {

        // Call parent RuntimeException constructor
        super(errorMessage);

        this.status = status;
    }

    /**
     * Get HTTP status of the exception.
     *
     * @return HTTP status
     */
    public HttpStatus getStatus() {
        return status;
    }
}