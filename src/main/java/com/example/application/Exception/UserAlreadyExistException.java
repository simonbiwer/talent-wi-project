package com.example.application.Exception;

/**
 *  @author tb 24.05.23
 *  @since 24.05.23
 * Exception thrown by system in case someone tries to register with already existing email
 * id in the system.
 */
public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException() {
        super();
    }


    public UserAlreadyExistException(String message) {
        super(message);
    }


    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
