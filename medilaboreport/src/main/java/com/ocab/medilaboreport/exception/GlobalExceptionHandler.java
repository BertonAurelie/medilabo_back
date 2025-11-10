package com.ocab.medilaboreport.exception;

import com.ocab.medilaboreport.model.ErrorEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MedilaboReportException.class)
    public ResponseEntity<ErrorEntity> badRequestException(MedilaboReportException exception) {
        ErrorEntity error = new ErrorEntity(exception.getMessage());

        return ResponseEntity.status(HttpStatus.OK.value()).body(error);
    }
}
