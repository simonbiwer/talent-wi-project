package com.example.application.controls;

import com.example.application.controls.factories.UserFactory;
import com.example.application.dtos.RegistrationResult;
import com.example.application.dtos.UserDTOImpl;
import com.example.application.entities.User;
import com.example.application.repositories.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

@SpringBootTest
public class RegistrationControlTest {
    private static UserDTOImpl benutzer;

    @Autowired
    private RegistrationControl registrationControl;

    @MockBean
    private UserRepository userRepository;

    // Vor jedem Test eine neue Instanz von UserDTOImpl erstellen
    @BeforeAll
    public static void setUp() {
        benutzer = new UserDTOImpl();
    }

    // Nach jedem Test die Instanz auf null setzen
    @AfterAll
    public static void tearDown() {
        benutzer = null;
    }

    @Test
    public void testRegisterUserWithUnoccupiedEmail() {
        benutzer.setSalutation("Herr");
        benutzer.setFirstname("Max");
        benutzer.setLastname("Mustermann");
        benutzer.setDateOfBirth(LocalDate.of(1990, 1, 1));
        benutzer.setEmail("max.mustermann@example.com");
        benutzer.setPassword("password123");

        User userEntity = UserFactory.createUser(benutzer);

        Mockito.when(userRepository.findUserByEmail(benutzer.getEmail())).thenReturn(null);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(userEntity);

        RegistrationResult result = registrationControl.registerUser(benutzer);

        assertTrue(result.OK());
        assertEquals("Registrierung erfolgreich!", result.getMessage());
    }
    @Test
    public void testRegisterUserWithOccupiedEmail() {
        benutzer.setSalutation("Herr");
        benutzer.setFirstname("Max");
        benutzer.setLastname("Mustermann");
        benutzer.setDateOfBirth(LocalDate.of(1990, 1, 1));
        benutzer.setEmail("max.mustermann@example.com");
        benutzer.setPassword("password123");

        Mockito.when(userRepository.findUserByEmail(benutzer.getEmail())).thenReturn(new User());

        RegistrationResult result = registrationControl.registerUser(benutzer);

        assertFalse(result.OK());
        assertEquals("Diese Email ist bereits vergeben", result.getMessage());
    }

    @Test
    public void testRegisterUser_success() {
        benutzer.setSalutation("Herr");
        benutzer.setFirstname("Max");
        benutzer.setLastname("Mustermann");
        benutzer.setDateOfBirth(LocalDate.of(1990, 1, 1));
        benutzer.setEmail("max.mustermann@example.com");
        benutzer.setPassword("password123");

        User userEntity = UserFactory.createUser(benutzer);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(userEntity);

        RegistrationResult result = registrationControl.registerUser(benutzer);

        assertTrue(result.OK());
        assertEquals("Registrierung erfolgreich!", result.getMessage());
    }

    @Test
    public void testRegisterUser_error() {
        benutzer.setSalutation("Herr");
        benutzer.setFirstname("Max");
        benutzer.setLastname("Mustermann");
        benutzer.setDateOfBirth(LocalDate.of(1990, 1, 1));
        benutzer.setEmail("max.mustermann@example.com");
        benutzer.setPassword("password123");

        //User userEntity = UserFactory.createUser(userDTOImpl);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenThrow(new RuntimeException());

        RegistrationResult result = registrationControl.registerUser(benutzer);

        assertFalse(result.OK());
        assertEquals("Fehler beim Abspeichern in die Datenbank: Admin kontaktieren", result.getMessage());
    }

}