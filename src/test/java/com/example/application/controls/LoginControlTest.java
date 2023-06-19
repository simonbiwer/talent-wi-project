package com.example.application.controls;

import com.example.application.dtos.LoginResultDTO;
import com.example.application.entities.User;
import com.example.application.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessResourceFailureException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


/**
 * Dies ist die Testklasse für die LoginControl-Klasse.
 * @author  Mhalah
 * @since 10.05.2023
 */

@SpringBootTest
class LoginControlTest {

    @Autowired
    private LoginControl loginControl;

    /**
     * Das Mock-Objekt für das UserRepository.
     */
    @MockBean
    private UserRepository userRepository;

    private User testUser;
    private User testUser2;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setEmail("test@test.com");
        testUser.setPasswort("test123");
        testUser2 = new User();

    }

    @Test
    void testAuthentificate_mitRichtigenAngaben() {
        // arrange
        when(userRepository.findUserByEmail(testUser.getEmail())).thenReturn(testUser);
        // act
        LoginResultDTO result = loginControl.authentificate(testUser.getEmail(), testUser.getPasswort());
        // assert
        assertTrue(result.getResult());
        assertEquals("LogIn erfolgreich", result.getReason());
        assertEquals(testUser, loginControl.getCurrentUser());
    }

    @Test
    void testAuthentificate_mitFalschenAngaben() {
        // arrange
        when(userRepository.findUserByEmail(testUser.getEmail())).thenReturn(null);
        // act
        LoginResultDTO result = loginControl.authentificate(testUser.getEmail(), testUser.getPasswort());
        // assert
        assertFalse(result.getResult());
        assertEquals("Benutzername oder Passwort falsch", result.getReason());
        assertNull(loginControl.getCurrentUser());
    }

    @Test
    void testAuthentificate_mitFalschemPassword() {
        // arrange
        User userWithWrongPassword = new User();
        userWithWrongPassword.setEmail("test@test.com");
        userWithWrongPassword.setPasswort("wrongPassword");
        when(userRepository.findUserByEmail(testUser.getEmail())).thenReturn(userWithWrongPassword);
        // act
        LoginResultDTO result = loginControl.authentificate(testUser.getEmail(), testUser.getPasswort());
        // assert
        assertFalse(result.getResult());
        assertEquals("Das eingegebene Password ist falsch!", result.getReason());

    }

    @Test
    void testAuthentificate_mitDatenbankFehler() {
        // arrange
        when(userRepository.findUserByEmail(testUser2.getEmail())).thenThrow(new DataAccessResourceFailureException("Database connection error"));
        // act
        LoginResultDTO result = loginControl.authentificate(testUser2.getEmail(), testUser2.getPasswort());
        // assert
        assertFalse(result.getResult());
        assertNull(loginControl.getCurrentUser());
    }
}