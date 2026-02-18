package com.railconnect.bookingservice.utils;

import com.railconnect.bookingservice.model.ErrorDetails; // Fixes ErrorDetails
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime; 
import com.railconnect.bookingservice.utils.PnrNotFoundException;// Fixes LocalDateTime

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handling specific custom exception
    @ExceptionHandler(PnrNotFoundException.class)
    public ResponseEntity<ErrorDetails> handlePnrNotFoundException(PnrNotFoundException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(), 
            exception.getMessage(), 
            webRequest.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Handling global exceptions (like database connection issues)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(), 
            exception.getMessage(), 
            webRequest.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}