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

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MedilaboPatientException.class)
    public ResponseEntity<ErrorEntity> badRequestException(MedilaboPatientException exception) {
        ErrorEntity error = new ErrorEntity(exception.getMessage());

        return ResponseEntity.status(HttpStatus.OK.value()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorEntity> MethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<String> errorList = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            errorList.add(fieldError.getDefaultMessage());
        }
        ErrorEntity error = new ErrorEntity(errorList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorEntity> HttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        ErrorEntity error = new ErrorEntity(exception.getMessage());

        return ResponseEntity.status(HttpStatus.OK.value()).body(error);
    }


}
