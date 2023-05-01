package com.example.application.controls.factories;

import com.example.application.dtos.UserDTOImpl;
import com.example.application.entities.User;

/**
 * Factory-Klasse zur Erzeugung von neuen Usern
 * @author sb
 * @since 01.05.23
 */

public class UserFactory {

    public static User createUser(UserDTOImpl userDTOImpl){
        User user = new User();
        user.setVorname(userDTOImpl.getFirstname());
        user.setNachname(userDTOImpl.getLastname());
        user.setEmail(userDTOImpl.getEmail());
        user.setPasswort(userDTOImpl.getPassword());
        return user;
    }
}
