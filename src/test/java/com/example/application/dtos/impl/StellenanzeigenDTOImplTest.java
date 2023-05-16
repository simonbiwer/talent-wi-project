package com.example.application.dtos.impl;
import com.example.application.dtos.KeywordDTO;
import com.example.application.dtos.impl.StellenanzeigenDTOImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Dies ist die Testklasse für die StellenanzeigenDTOImpl-Klasse.
 * @author  Mhalah
 * @since 16.05.2023
 */

class StellenanzeigenDTOImplTest {

    @Test
    void setTitel_SollteTitelSetzen() {
        // Arrange
        String expectedTitel = "Softwareentwickler";
        StellenanzeigenDTOImpl stellenanzeigenDTO = new StellenanzeigenDTOImpl();

        // Act
        stellenanzeigenDTO.setTitel(expectedTitel);
        String actualTitel = stellenanzeigenDTO.getTitel();

        // Assert
        assertEquals(expectedTitel, actualTitel);
    }

    @Test
    void setKeywords_SollteKeywordsSetzen() {
        // Arrange
        List<KeywordDTO> expectedKeywords = new ArrayList<>();
        KeywordDTO keyword1 = new KeywordDTOImpl();
        keyword1.setKeywordname("Java");
        expectedKeywords.add(keyword1);
        KeywordDTO keyword2 = new KeywordDTOImpl();
        keyword2.setKeywordname("Spring");
        expectedKeywords.add(keyword2);
        StellenanzeigenDTOImpl stellenanzeigenDTO = new StellenanzeigenDTOImpl();

        // Act
        stellenanzeigenDTO.setKeywords(expectedKeywords);
        List<KeywordDTO> actualKeywords = stellenanzeigenDTO.getKeywords();

        // Assert
        assertNotNull(actualKeywords);
        assertEquals(expectedKeywords.size(), actualKeywords.size());
        for (int i = 0; i < expectedKeywords.size(); i++) {
            KeywordDTO expectedKeyword = expectedKeywords.get(i);
            KeywordDTO actualKeyword = actualKeywords.get(i);
            assertEquals(expectedKeyword.getKeywordid(), actualKeyword.getKeywordid());
            assertEquals(expectedKeyword.getKeywordname(), actualKeyword.getKeywordname());
        }
    }
}
