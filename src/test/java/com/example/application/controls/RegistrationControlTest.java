package com.example.application.controls;

import com.example.application.controls.factories.UserFactory;
import com.example.application.dtos.RegistrationResult;
import com.example.application.dtos.UserDTO;
import com.example.application.dtos.UserDTOImpl;
import com.example.application.entities.User;
import com.example.application.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

@SpringBootTest
public class RegistrationControlTest {

    @Autowired
    private RegistrationControl registrationControl;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testRegisterUser_success() {
        UserDTOImpl userDTOImpl = new UserDTOImpl();
        userDTOImpl.setSalutation("Herr");
        userDTOImpl.setFirstname("Max");
        userDTOImpl.setLastname("Mustermann");
        userDTOImpl.setDateOfBirth(LocalDate.of(1990, 1, 1));
        userDTOImpl.setEmail("max.mustermann@example.com");
        userDTOImpl.setPassword("password123");

        User userEntity = UserFactory.createUser(userDTOImpl);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(userEntity);

        RegistrationResult result = registrationControl.registerUser(userDTOImpl);

        assertTrue(result.OK());
        assertEquals("Registrierung erfolgreich!", result.getMessage());
    }

    @Test
    public void testRegisterUser_error() {
        UserDTOImpl userDTOImpl = new UserDTOImpl();
        userDTOImpl.setSalutation("Herr");
        userDTOImpl.setFirstname("Max");
        userDTOImpl.setLastname("Mustermann");
        userDTOImpl.setDateOfBirth(LocalDate.of(1990, 1, 1));
        userDTOImpl.setEmail("max.mustermann@example.com");
        userDTOImpl.setPassword("password123");

        User userEntity = UserFactory.createUser(userDTOImpl);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenThrow(new RuntimeException());

        RegistrationResult result = registrationControl.registerUser(userDTOImpl);

        assertFalse(result.OK());
        assertEquals("Fehler beim Abspeichern in die Datenbank: Admin kontaktieren", result.getMessage());
    }
}
