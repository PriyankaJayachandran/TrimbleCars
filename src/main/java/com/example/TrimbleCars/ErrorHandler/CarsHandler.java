package com.example.TrimbleCars.ErrorHandler;

public class CarsHandler {
    protected ErrorResponse createTPPClientException(Exception ex) {
        return ErrorResponse.builder()
                .errorCode(400)
                .errorMessage(ex.getMessage())
                .build();
    }
}
