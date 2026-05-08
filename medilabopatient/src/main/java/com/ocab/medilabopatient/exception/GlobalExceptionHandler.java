package com.ocab.medilabopatient.exception;

import com.ocab.medilabopatient.model.ErrorEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Global exception handler for the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle custom MedilaboPatientException.
     *
     * @param exception custom exception
     * @return error response
     */
    @ExceptionHandler(MedilaboPatientException.class)
    public ResponseEntity<ErrorEntity> badRequestException(MedilaboPatientException exception) {

        // Create error entity with exception message
        ErrorEntity error = new ErrorEntity(exception.getMessage());

        return ResponseEntity.status(HttpStatus.OK.value()).body(error);
    }

    /**
     * Handle validation errors from request body.
     *
     * @param exception validation exception
     * @return list of validation errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorEntity> MethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        // Get validation field errors
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        // Store error messages
        List<String> errorList = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            errorList.add(fieldError.getDefaultMessage());
        }

        ErrorEntity error = new ErrorEntity(errorList);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    /**
     * Handle invalid or unreadable HTTP request body.
     *
     * @param exception unreadable message exception
     * @return error response
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorEntity> HttpMessageNotReadableException(
            HttpMessageNotReadableException exception) {

        // Create error entity with exception message
        ErrorEntity error = new ErrorEntity(exception.getMessage());

        return ResponseEntity.status(HttpStatus.OK.value()).body(error);
    }
}