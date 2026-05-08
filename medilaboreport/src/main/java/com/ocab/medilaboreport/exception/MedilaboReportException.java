package com.ocab.medilaboreport.exception;

/**
 * Custom exception for the Medilabo Report application.
 */
public class MedilaboReportException extends RuntimeException {

    /**
     * Constructor for MedilaboReportException.
     *
     * @param message error message
     */
    public MedilaboReportException(String message) {
        super(message);
    }
}