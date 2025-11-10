package com.VimalChaudhary.SwiftRide.exception;

public class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException(String message) {
        super(message);
        System.err.println("\u001B[31mError: Resource not found\u001B[0m"); // red Colored error on console
    }
}
