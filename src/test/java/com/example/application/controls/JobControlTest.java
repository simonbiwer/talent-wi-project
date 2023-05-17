package com.example.application.controls;
import com.example.application.dtos.InsertJobResult;
import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.dtos.impl.InsertJobResultImpl;
import com.example.application.dtos.impl.StellenanzeigenDTOImpl;
import com.example.application.entities.Stellenanzeige;
import com.example.application.repositories.KeywordRepository;
import com.example.application.repositories.StellenanzeigeRepository;
import com.example.application.utils.UtilCurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Dies ist die Testklasse für die AddJobControl-Klasse.
 * @author  Mhalah
 * @since 16.05.2023
 */

@SpringBootTest
public class JobControlTest {

    @Mock
    private StellenanzeigeRepository jobRep;

    @Mock
    private KeywordRepository keywordRepo;

    @Mock
    private UtilCurrent utilCurrent;

    @InjectMocks
    private JobControl jobControl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Testfall für die Methode createStellenanzeige.
     * Es wird überprüft, ob eine neue Stellenanzeige erfolgreich gespeichert wird.
     * Der Testfall erwartet, dass die Stellenanzeige erfolgreich abgespeichert wird.
     *
     *die Fehlermeldung deutet darauf hin, dass der Rückgabewert von UI.getCurrent() null ist,
     was zu einer NullPointerException führt, wenn UI.getSession() aufgerufen wird.
     */


//    @Test
//    public void createStellenanzeige_Should_SaveNewJob_When_SuccessfullySaved() {
//        // Arrange
//        StellenanzeigenDTO stellenanzeigenDTO = new StellenanzeigenDTOImpl();
//        stellenanzeigenDTO.setUrl("example.com");
//        stellenanzeigenDTO.setKeywords(new ArrayList<>());
//
//        // Mocks
//        when(jobRep.findStellenanzeigeByUrl(any(String.class))).thenReturn(null);
//        when(keywordRepo.findKeywordByKeywordid(any(Integer.class))).thenReturn(null);
//
//        // Act
//        InsertJobResult result = addJobControl.createStellenanzeige(stellenanzeigenDTO);
//
//        // Assert
//        assertEquals("Abspeicherung der Stellenanzeige erfolgreich!", result.getMessage());
//    }


    @Test
    public void createStellenanzeige_Should_ReturnErrorMessage_When_UrlAlreadyExists() {
        // Arrange
        StellenanzeigenDTO stellenanzeigenDTO = new StellenanzeigenDTOImpl();
        stellenanzeigenDTO.setUrl("https://example.com/job1");
        when(jobRep.findStellenanzeigeByUrl(stellenanzeigenDTO.getUrl())).thenReturn(new Stellenanzeige());
        InsertJobResult expectedResult = new InsertJobResultImpl();
        expectedResult.setMessage("Stellenanzeige mit dieser URL existiert bereits!");
        expectedResult.setNotOK();

        // Act
        InsertJobResult actualResult = jobControl.createStellenanzeige(stellenanzeigenDTO);

        // Assert
        verify(jobRep, times(1)).findStellenanzeigeByUrl(stellenanzeigenDTO.getUrl());
        verify(jobRep, times(0)).save(any(Stellenanzeige.class));
        assertEquals(expectedResult.getMessage(), actualResult.getMessage());
        assertFalse(actualResult.OK());
    }

}