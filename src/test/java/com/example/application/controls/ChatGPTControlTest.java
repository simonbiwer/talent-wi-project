package com.example.application.controls;

import com.example.application.entities.Stellenanzeige;
import com.example.application.repositories.StellenanzeigeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * @author mhalah
 * @since 17.05.2023
 * ChatGPTControl Test mit Mockito
 */

class ChatGPTControlTest {

    @Mock
    private StellenanzeigeRepository jobRepo;

    @InjectMocks
    private ChatGPTControl chatGPTControl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    /*
    @Test
    void crawlDataWithChatGPT_SollteTrueZurückgeben_WennDatenErfolgreichGecrawlt() {
        // Arrange
        String jobUrl = "https://example.com/job";
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("Technologie", "keine Angabe"); // Anpassung hier

        // Das Verhalten von jobRepo.findStellenanzeigeByUrl() mocken
        Stellenanzeige job = new Stellenanzeige();
        when(jobRepo.findStellenanzeigeByUrl(jobUrl)).thenReturn(job);

        // Act
        boolean result = chatGPTControl.crawlDataWithChatGPT(jobUrl);

        // Assert
        assertTrue(result);
        assertNotNull(job);
        assertEquals("'keine Angabe'", job.getTechnologien()); // Anpassung hier
        // ...

        // Überprüfen, ob jobRepo.save(job) aufgerufen wurde
        verify(jobRepo, times(1)).save(job);
    }

    @Test
    void crawlDataWithChatGPT_SollteFalseZurückgeben_BeiAuftretenEinerException() {
        // Arrange
        String jobUrl = "https://example.com/job";

        // Das Verhalten von jobRepo.findStellenanzeigeByUrl() mocken
        when(jobRepo.findStellenanzeigeByUrl(jobUrl)).thenReturn(null);

        // Act
        boolean result = chatGPTControl.crawlDataWithChatGPT(jobUrl);

        // Assert
        assertFalse(result);
    }
    */
}