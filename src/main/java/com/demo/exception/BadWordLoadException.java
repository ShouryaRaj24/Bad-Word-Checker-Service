package com.demo.exception;


public class BadWordLoadException extends Exception {

    public BadWordLoadException(String message) {
        super(message);
    }

    public BadWordLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
