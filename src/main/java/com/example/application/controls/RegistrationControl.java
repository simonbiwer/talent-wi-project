package com.example.application.controls;

import com.example.application.Service.Token.SecureTokenService;
import com.example.application.controls.factories.UserFactory;
import com.example.application.dtos.impl.RegistrationResultDTOImpl;
import com.example.application.dtos.impl.UserDTOImpl;
import com.example.application.entities.SecureToken;
import com.example.application.entities.User;
import com.example.application.repositories.SecureTokenRepository;
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

    @Autowired
    private SecureTokenService secureTokenService;

    @Autowired
    private SecureTokenRepository secureTokenRepository;

    /**
     * Registriert einen neuen Benutzer, falls die übergebene Email noch nicht in der Datenbank existiert.
     * @param userDTOImpl - ein UserDTOImpl-Objekt, das Informationen über den neuen Benutzer enthält.
     * @return ein RegistrationResultDTOImpl-Objekt, das den Erfolg der Registrierung und eine Nachricht enthält.
     */
    public RegistrationResultDTOImpl registerUser(UserDTOImpl userDTOImpl){
        RegistrationResultDTOImpl result = new RegistrationResultDTOImpl();
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

    /**
     * Überprüft, ob eine bestimmte Email bereits in der Datenbank existiert.
     * @param email - die Email-Adresse, die überprüft werden soll.
     * @return true, wenn die Email bereits in der Datenbank existiert, andernfalls false.
     */
    public boolean checkIfEmailOccupied(String email) {
        //Datenbank Zugriff mit boolean Wert ob email bereits in der Datenbank existiert
        return !(userRep.findUserByEmail(email) == null);
    }

    public void sendRegistrationConfirmationEmail(User user) {
        SecureToken secureToken = secureTokenService.createSecureToken();
        secureToken.setUser(user);
        secureTokenRepository.save(secureToken);
    }


}