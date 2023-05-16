package com.example.application.dtos.impl;
import com.example.application.dtos.KeywordDTO;
import com.example.application.dtos.impl.KeywordDTOImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Dies ist die Testklasse für die KeywordDTOImpl-Klasse.
 * @author  Mhalah
 * @since 16.05.2023
 */

class KeywordDTOImplTest {

    @Test
    void getKeywordname_SollteRichtigesKeywordnameZurückgeben() {
        // Arrange
        String expectedKeywordName = "Java";
        KeywordDTO keywordDTO = new KeywordDTOImpl();
        keywordDTO.setKeywordname(expectedKeywordName);

        // Act
        String actualKeywordName = keywordDTO.getKeywordname();

        // Assert
        assertEquals(expectedKeywordName, actualKeywordName);
    }

    @Test
    void setKeywordname_SollteKeywordnameSetzen() {
        // Arrange
        String expectedKeywordName = "Python";
        KeywordDTO keywordDTO = new KeywordDTOImpl();

        // Act
        keywordDTO.setKeywordname(expectedKeywordName);
        String actualKeywordName = keywordDTO.getKeywordname();

        // Assert
        assertEquals(expectedKeywordName, actualKeywordName);
    }
}
