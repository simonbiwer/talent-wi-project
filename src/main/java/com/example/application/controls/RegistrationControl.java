package com.example.application.controls;

import com.example.application.controls.factories.UserFactory;
import com.example.application.dtos.RegistrationResult;
import com.example.application.dtos.UserDTOImpl;
import com.example.application.entities.User;
import com.example.application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Control-Klasse zum Abwickeln der Registrierung
 * @author sb
 * @since 01.05.23
 */

@Component
public class RegistrationControl {

    @Autowired
    private UserRepository userRep;

    public RegistrationResult registerUser(UserDTOImpl userDTOImpl){
        RegistrationResult result = new RegistrationResult();
        if (checkIfEmailOccupied(userDTOImpl.getEmail())){
            result.setMessage("Diese Email ist bereits vergeben");
            result.setNotOK();
        }
        if (result.OK()){
            User userEntity = UserFactory.createUser(userDTOImpl);
            try{
                userRep.save(userEntity);
            } catch (Exception e){
                result.setNotOK();
                result.setMessage("Fehler beim Abspeichern in die Datenbank: Admin kontaktieren");
                userRep.delete(userEntity);
            }

            if (result.OK()) {
                result.setMessage("Registrierung erfolgreich!");
            }
        }
        return result;
    }

    public boolean checkIfEmailOccupied(String email) {
        //Datenbank Zugriff mit boolean Wert ob email bereits in der Datenbank existiert
        return (userRep.findUserByEmail(email) != null);
    }
}
