package com.railconnect.bookingservice.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PnrNotFoundException extends RuntimeException { // This fixes the Type mismatch
    public PnrNotFoundException(String message) {
        super(message); // This fixes getMessage() undefined
    }
}