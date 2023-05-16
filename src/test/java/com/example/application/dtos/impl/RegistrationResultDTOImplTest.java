package com.example.application.dtos.impl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Dies ist die Testklasse für die RegistrationResultDTOImpl-Klasse.
 * @author  Mhalah
 * @since 16.05.2023
 */


class RegistrationResultDTOImplTest {

    @Test
    void setMessage_SollteNachrichtSetzen() {
        // Arrange
        String expectedMessage = "Registrierung erfolgreich";
        RegistrationResultDTOImpl registrationResultDTO = new RegistrationResultDTOImpl();

        // Act
        registrationResultDTO.setMessage(expectedMessage);
        String actualMessage = registrationResultDTO.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getMessage_SollteNachrichtZurückgeben() {
        // Arrange
        String expectedMessage = "Bitte füllen Sie alle Pflichtfelder aus";
        RegistrationResultDTOImpl registrationResultDTO = new RegistrationResultDTOImpl();
        registrationResultDTO.setMessage(expectedMessage);

        // Act
        String actualMessage = registrationResultDTO.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setNotOK_SollteRegistrierungsergebnisNichtOKSetzen() {
        // Arrange
        RegistrationResultDTOImpl registrationResultDTO = new RegistrationResultDTOImpl();

        // Act
        registrationResultDTO.setNotOK();
        boolean actualResult = registrationResultDTO.OK();

        // Assert
        assertFalse(actualResult);
    }

    @Test
    void OK_SollteRegistrierungsergebnisÜberprüfen() {
        // Arrange
        RegistrationResultDTOImpl registrationResultDTO = new RegistrationResultDTOImpl();

        // Act
        boolean actualResult = registrationResultDTO.OK();

        // Assert
        assertTrue(actualResult);
    }
}