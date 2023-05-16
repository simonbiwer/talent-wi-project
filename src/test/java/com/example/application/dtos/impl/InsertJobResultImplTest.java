package com.example.application.dtos.impl;

import com.example.application.dtos.InsertJobResult;
import com.example.application.dtos.impl.InsertJobResultImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Dies ist die Testklasse für die InsertJobResultImpl-Klasse.
 * @author  Mhalah
 * @since 10.05.2023
 */

class InsertJobResultImplTest {

    @Test
    void getMessage_SollteRichtigesMessageZurückgeben() {
        // Arrange
        String expectedMessage = "Erfolgreich gespeichert";
        InsertJobResult result = new InsertJobResultImpl();
        result.setMessage(expectedMessage);

        // Act
        String actualMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void OK_SollteTrueZurückgeben() {
        // Arrange
        InsertJobResult result = new InsertJobResultImpl();

        // Act
        boolean isOK = result.OK();

        // Assert
        assertTrue(isOK);
    }

    @Test
    void setNotOK_OK_SollteFalseZurückgeben() {
        // Arrange
        InsertJobResult result = new InsertJobResultImpl();

        // Act
        result.setNotOK();
        boolean isOK = result.OK();

        // Assert
        assertFalse(isOK);
    }
}
