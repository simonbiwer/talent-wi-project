package com.example.application.controls;

import com.example.application.controls.factories.UserFactory;
import com.example.application.dtos.RegistrationResult;
import com.example.application.dtos.UserDTO;
import com.example.application.entities.User;
import com.example.application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationControl {

    @Autowired
    private UserRepository userRep;

    public RegistrationResult registerUser(UserDTO userDTO){
        RegistrationResult result = new RegistrationResult();
        User userEntity = UserFactory.createUser(userDTO);
        try{
            userRep.save(userEntity);
            result.setMessage("Registrierung erfolgreich!");
        } catch (Exception e){
            userRep.delete(userEntity);
            result.setNotOK();
            result.setMessage("Fehler beim Abspeichern in die Datenbank: Admin kontaktieren");
        }
        return result;
    }
}
