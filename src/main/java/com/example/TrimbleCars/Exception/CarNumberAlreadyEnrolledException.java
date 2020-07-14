package com.example.TrimbleCars.Exception;

public class CarNumberAlreadyEnrolledException extends RuntimeException {
    public CarNumberAlreadyEnrolledException(String s) {
        super(s);
    }
}
