package com.example.application.controls;
import com.example.application.controls.factories.StellenanzeigenFactory;
import com.example.application.dtos.InsertJobResult;
import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.dtos.impl.InsertJobResultImpl;
import com.example.application.dtos.impl.StellenanzeigenDTOImpl;
import com.example.application.entities.Keyword;
import com.example.application.entities.Stellenanzeige;
import com.example.application.entities.User;
import com.example.application.repositories.KeywordRepository;
import com.example.application.repositories.StellenanzeigeRepository;
import com.example.application.utils.UtilCurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

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


    @Test
    public void createStellenanzeige_Should_CreateStellenanzeigeObject_WithCorrectValues() {
        // Create a sample DTO with test values
        StellenanzeigenDTO dto = new StellenanzeigenDTOImpl();
        dto.setTitel("Test Title");
        dto.setBeschreibung("Test Description");
        dto.setUrl("www.example.com");
        dto.setQualifikation("Test Qualification");
        dto.setProjektdauer("Test Duration");
        dto.setStartdatum("2023-05-16");
        dto.setTechnologien("Java, Spring");
        dto.setUnternehmen("Test Company");
        dto.setReserved(true);

        // Create a sample user object
        User user = new User(); // Set up the necessary properties of the user object

        // Create a sample list of keywords
        List<Keyword> keywords = new ArrayList<>();
        Keyword kw1 = new Keyword();
        kw1.setKeywordname("testKeyword");
        Keyword kw2 = new Keyword();
        kw2.setKeywordname("testKeyword2");
        // Add keywords to the list
        keywords.add(kw1);
        keywords.add(kw2);

        // Call the createStellenanzeige method of the factory
        Stellenanzeige result = StellenanzeigenFactory.createStellenanzeige(dto, user, keywords);

        // Assert the properties of the created Stellenanzeige object
        assertEquals(dto.getTitel(), result.getTitel());
        assertEquals(dto.getBeschreibung(), result.getBeschreibung());
        assertEquals(dto.getUrl(), result.getUrl());
        assertEquals(dto.getQualifikation(), result.getQualifikation());
        assertEquals(dto.getProjektdauer(), result.getProjektdauer());
        assertEquals(dto.getStartdatum(), result.getStartdatum());
        assertEquals(dto.getTechnologien(), result.getTechnologien());
        assertEquals(dto.getUnternehmen(), result.getUnternehmen());
        assertEquals(dto.getReserved(), result.getReserved());
        assertEquals(user, result.getUserid());
        assertEquals(keywords.size(), result.getKeywords().size());
    }



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