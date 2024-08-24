package com.demo.exception;


public class CheckSentenceException extends Exception {

    public CheckSentenceException(String message) {
        super(message);
    }

    public CheckSentenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
