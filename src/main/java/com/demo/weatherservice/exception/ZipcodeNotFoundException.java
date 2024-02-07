package com.demo.weatherservice.exception;

public class ZipcodeNotFoundException extends RuntimeException {
    public ZipcodeNotFoundException(String message) {
            super(message);
        }

    public ZipcodeNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
}
