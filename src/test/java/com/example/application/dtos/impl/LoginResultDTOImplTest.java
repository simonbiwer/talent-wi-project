package com.example.application.dtos.impl;
import com.example.application.dtos.LoginResultDTO;
import com.example.application.dtos.impl.LoginResultDTOImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Dies ist die Testklasse für die LoginResultDTOImpl-Klasse.
 * @author  Mhalah
 * @since 16.05.2023
 */

class LoginResultDTOImplTest {

    @Test
    void setResult_SollteResultSetzen() {
        // Arrange
        boolean expectedResult = true;
        LoginResultDTO loginResultDTO = new LoginResultDTOImpl();

        // Act
        loginResultDTO.setResult(expectedResult);
        boolean actualResult = loginResultDTO.getResult();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void setReason_SollteReasonSetzen() {
        // Arrange
        String expectedReason = "Ungültige Anmeldeinformationen";
        LoginResultDTO loginResultDTO = new LoginResultDTOImpl();

        // Act
        loginResultDTO.setReason(expectedReason);
        String actualReason = loginResultDTO.getReason();

        // Assert
        assertEquals(expectedReason, actualReason);
    }

    @Test
    void getResult_SollteResultZurückgeben() {
        // Arrange
        boolean expectedResult = false;
        LoginResultDTO loginResultDTO = new LoginResultDTOImpl();
        loginResultDTO.setResult(expectedResult);

        // Act
        boolean actualResult = loginResultDTO.getResult();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getReason_SollteReasonZurückgeben() {
        // Arrange
        String expectedReason = "Benutzerkonto deaktiviert";
        LoginResultDTO loginResultDTO = new LoginResultDTOImpl();
        loginResultDTO.setReason(expectedReason);

        // Act
        String actualReason = loginResultDTO.getReason();

        // Assert
        assertEquals(expectedReason, actualReason);
    }
}
