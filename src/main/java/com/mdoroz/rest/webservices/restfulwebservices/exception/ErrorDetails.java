package com.mdoroz.rest.webservices.restfulwebservices.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDetails {

    public ErrorDetails(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    private LocalDateTime timestamp;
    private String message;
    private String details;

}
