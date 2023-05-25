package com.example.application.Exception;

/**
 * @author tb 24.05.23
 * @since 24.05.23
 * Exception, die irgendwann inshallah geworfen wird
 */

public class UnknownIdentifierException extends Exception {

    public UnknownIdentifierException() {
        super();
    }


    public UnknownIdentifierException(String message) {
        super(message);
    }


    public UnknownIdentifierException(String message, Throwable cause) {
        super(message, cause);
    }
}

