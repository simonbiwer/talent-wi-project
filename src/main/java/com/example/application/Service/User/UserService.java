package com.example.application.Service.User;

import com.example.application.Exception.InvalidTokenException;
import com.example.application.Exception.UnknownIdentifierException;
import com.example.application.dtos.impl.RegistrationResultDTOImpl;
import com.example.application.dtos.impl.UserDTOImpl;
import com.example.application.entities.User;

/**
 * Interface für den UserService zur Registrierung eines Users
 */
public interface UserService {

    RegistrationResultDTOImpl registerUser(final UserDTOImpl user);

    boolean checkIfEmailOccupied(final String email);

    void sendRegistrationConfirmationEmail(final User user);

    boolean verifyUser(final String token) throws InvalidTokenException;

    User getUserById(final String id) throws UnknownIdentifierException;
}

