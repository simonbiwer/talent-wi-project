package com.example.application.controls;


import com.example.application.dtos.LoginResultDTO;
import com.example.application.dtos.UserDTO;
import com.example.application.dtos.impl.LoginResultDTOImpl;
import com.example.application.entities.User;
import com.example.application.repositories.UserRepository;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginControl {
    @Autowired
    private UserRepository repository;

    private User user = null;

    private LoginResultDTOImpl loginResult = new LoginResultDTOImpl();

    public LoginResultDTO authentificate(String email, String plainTextPassword ) {

        // User wird per SQL ausgelesen
        this.user = this.getUser(email , plainTextPassword );

        if (this.user == null) {
            loginResult.setResult(false);
            loginResult.setReason("Benutzername oder Passwort falsch");
        }
        return loginResult;
    }

    public User getCurrentUser(){
        return this.user;
    }

    protected User getUser(String email , String plainTextPassword ) {
        User userTmp = null;
        try {
            userTmp = repository.findUserByEmail(email);
            if (userTmp == null) {
                return null;
            }
            if (plainTextPassword.equals(userTmp.getPasswort())) {
                loginResult.setResult(true);
                loginResult.setReason("LogIn erfolgreich");
            } else {
                loginResult.setResult(false);
                loginResult.setReason("Das eingegebene Password ist falsch!");
            }
        } catch ( org.springframework.dao.DataAccessResourceFailureException e ) {
            loginResult.setResult(false);
            loginResult.setReason("Es ist ein Fehler während der Verbindung zur Datenbank aufgetreten: " + e);
        }
        return userTmp;
    }

}
