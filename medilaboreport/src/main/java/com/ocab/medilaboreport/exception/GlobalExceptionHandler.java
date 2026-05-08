package com.ocab.medilaboreport.exception;

import com.ocab.medilaboreport.model.ErrorEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles MedilaboReportException exceptions.
     *
     * @param exception custom application exception
     * @return error response with message
     */
    @ExceptionHandler(MedilaboReportException.class)
    public ResponseEntity<ErrorEntity> badRequestException(MedilaboReportException exception) {

        // Create error object with exception message
        ErrorEntity error = new ErrorEntity(exception.getMessage());

        // Return HTTP response with error message
        return ResponseEntity.status(HttpStatus.OK.value()).body(error);
    }
}