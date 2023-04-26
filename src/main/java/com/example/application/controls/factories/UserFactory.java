package com.example.application.controls.factories;

import com.example.application.dtos.UserDTO;
import com.example.application.entities.User;

public class UserFactory {

    public static User createUser(UserDTO userDTO){
        User user = new User();
        return user;
    }
}
