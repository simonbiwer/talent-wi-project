package com.example.application.Exception;

/**
 * @author tb 24.05.23
 * @since 24.05.23
 * Exception, die bei falschen Tokens bei der Verifizierung geworfen werden soll.
 */

public class InvalidTokenException extends Exception {

    public InvalidTokenException() {
        super();
    }


    public InvalidTokenException(String message) {
        super(message);
    }


    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
