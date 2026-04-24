package com.example.fundoonotes.exception;
/*
 * ErrorResponse
 * Standard structure for API error responses
 */


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private int statusCode;
    private LocalDateTime timestamp;
}