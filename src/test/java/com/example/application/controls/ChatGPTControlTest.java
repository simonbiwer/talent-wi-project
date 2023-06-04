package com.example.application.controls;

import com.example.application.dtos.impl.StellenanzeigenDTOImpl;
import com.example.application.entities.Stellenanzeige;
import com.example.application.repositories.StellenanzeigeRepository;
import com.example.application.utils.JobInjectService;
import com.example.application.utils.SettingsService;
import com.example.application.utils.UtilNavigation;
import com.vaadin.flow.component.UI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ChatGPTControlTest {

    @Mock
    private StellenanzeigeRepository jobRepo;

    @Mock
    private JobInjectService jobInjectService;

    @Mock
    private SettingsService settingsService;

    @Mock
    private UtilNavigation utilNavigation;

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
        String jobUrl = "https://www.stepstone.de/stellenangebote--Mitarbeiter-IT-Business-Operations-m-w-d-Neckarsulm-Schwarz-Dienstleistungen--9580457-inline.html?rltr=1_1_25_seorl_m_1_0_0_0_0_0";
        StellenanzeigenDTOImpl stellenanzeigenDTO = new StellenanzeigenDTOImpl();
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("Technologie", "keine Angabe");
        stellenanzeigenDTO.setUrl(jobUrl);

        // Das Verhalten von jobRepo.findStellenanzeigeByUrl() mocken
        Stellenanzeige job = new Stellenanzeige();
        when(jobRepo.findStellenanzeigeByUrl(jobUrl)).thenReturn(job);

        // Das Verhalten von UI.getCurrent() mocken
        UI uiInstance = mock(UI.class);
        UI.setCurrent(uiInstance);

        // Act
        boolean result = chatGPTControl.crawlDataWithChatGPT(stellenanzeigenDTO);

        // Assert
        assertTrue(result);
        assertNotNull(job);
        assertEquals("keine Angabe", job.getTechnologien());
        // ...

        // Überprüfen, ob jobRepo.save(job) aufgerufen wurde
        verify(jobRepo, times(1)).save(job);

        // Überprüfen, ob settingsService.setJobHinzufuegen(true) aufgerufen wurde
        verify(settingsService, times(1)).setJobHinzufuegen(true);

        // Überprüfen, ob UtilNavigation.navigateToJobAdvertisementEdit() aufgerufen wurde
        verify(utilNavigation, times(1)).navigateToJobAdvertisementEdit();
    } */

    @Test
    void crawlDataWithChatGPT_SollteFalseZurückgeben_BeiAuftretenEinerException() {
        // Arrange
        String jobUrl = "https://example.com/job";
        StellenanzeigenDTOImpl stellenanzeigenDTO  = new StellenanzeigenDTOImpl();
        stellenanzeigenDTO.setUrl(jobUrl);

        // Das Verhalten von jobRepo.findStellenanzeigeByUrl() mocken
        when(jobRepo.findStellenanzeigeByUrl(jobUrl)).thenReturn(null);

        // Act
        boolean result = chatGPTControl.crawlDataWithChatGPT(stellenanzeigenDTO);

        // Assert
        assertFalse(result);
    }
}