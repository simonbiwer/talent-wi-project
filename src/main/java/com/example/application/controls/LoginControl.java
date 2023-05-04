package com.example.application.controls;


import com.example.application.dtos.LoginResultDTO;
import com.example.application.dtos.UserDTO;
import com.example.application.dtos.impl.LoginResultDTOImpl;
import com.example.application.entities.User;
import com.example.application.repositories.UserRepository;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**

 Diese Klasse ist für die Authentifizierung des Benutzers zuständig.
 Sie verwendet das UserRepository-Objekt, um auf die Datenbank zuzugreifen, und validiert die Anmeldedaten des Benutzers.
 Wenn der Benutzername und das Passwort korrekt sind, wird ein LoginResultDTO-Objekt zurückgegeben.
 @author sb, hh
 @since 04.05.2023
 */

@Component
public class LoginControl {
    @Autowired
    private UserRepository repository;

    private User user = null;

    private LoginResultDTOImpl loginResult = new LoginResultDTOImpl();

    /**
     * Diese Methode authentifiziert einen Benutzer anhand der E-Mail-Adresse und des Passworts.
     * Wenn die Anmeldedaten des Benutzers korrekt sind, wird ein LoginResultDTO-Objekt zurückgegeben.
     *
     * @param email             Die E-Mail-Adresse des Benutzers.
     * @param plainTextPassword Das Klartext-Passwort des Benutzers.
     * @return Das LoginResultDTO-Objekt, das das Ergebnis der Authentifizierung enthält.
     */
    public LoginResultDTO authentificate(String email, String plainTextPassword) {

        // User wird per SQL ausgelesen
        this.user = this.getUser(email, plainTextPassword);

        if (this.user == null) {
            loginResult.setResult(false);
            loginResult.setReason("Benutzername oder Passwort falsch");
        }
        return loginResult;
    }

    /**
     * Diese Methode gibt den aktuell angemeldeten Benutzer zurück.
     *
     * @return Der aktuell angemeldete Benutzer.
     */
    public User getCurrentUser() {
        return this.user;
    }

    /**
     * Diese Methode ruft das UserRepository-Objekt auf, um den Benutzer anhand seiner E-Mail-Adresse aus der Datenbank abzurufen.
     * Wenn der Benutzer gefunden wird und das eingegebene Passwort mit dem Passwort des Benutzers übereinstimmt, wird das LoginResultDTO-Objekt aktualisiert.
     *
     * @param email             Die E-Mail-Adresse des Benutzers.
     * @param plainTextPassword Das Klartext-Passwort des Benutzers.
     * @return Der Benutzer, der anhand der E-Mail-Adresse aus der Datenbank abgerufen wurde, oder null, wenn der Benutzer nicht gefunden wird.
     */
    protected User getUser(String email, String plainTextPassword) {
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
        } catch (org.springframework.dao.DataAccessResourceFailureException e) {
            loginResult.setResult(false);
            loginResult.setReason("Es ist ein Fehler während der Verbindung zur Datenbank aufgetreten: " + e);
        }
        return userTmp;
    }
}
