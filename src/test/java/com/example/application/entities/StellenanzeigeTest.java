package com.example.application.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Dies ist die Testklasse für die Stellenanyeige-Klasse.
 * @author  Mhalah
 * @since 10.05.2023
 */

class StellenanzeigeTest {
    private Stellenanzeige stellenanzeige;

    @BeforeEach
    void setUp() {
        stellenanzeige = new Stellenanzeige();
    }

    @Test
    void testSetundGetTitel() {
        String titel = "Softwareentwickler (m/w/d)";
        assertNull(stellenanzeige.getTitel());
        stellenanzeige.setTitel(titel);
        assertEquals(titel, stellenanzeige.getTitel());
    }

    @Test
    void testSetundGetBeschreibung() {
        String beschreibung = "Wir suchen einen erfahrenen Softwareentwickler zur Unterstützung unseres Teams.";
        assertNull(stellenanzeige.getBeschreibung());
        stellenanzeige.setBeschreibung(beschreibung);
        assertEquals(beschreibung, stellenanzeige.getBeschreibung());
    }

    // weitere Testfälle hier ...

    @Test
    void testEqualsundHashCode() {
        Stellenanzeige sa1 = new Stellenanzeige();
        sa1.setJobid(1);
        sa1.setTitel("Softwareentwickler (m/w/d)");
        sa1.setBeschreibung("Wir suchen einen erfahrenen Softwareentwickler zur Unterstützung unseres Teams.");
        Stellenanzeige sa2 = new Stellenanzeige();
        sa2.setJobid(1);
        sa2.setTitel("Softwareentwickler (m/w/d)");
        sa2.setBeschreibung("Wir suchen einen erfahrenen Softwareentwickler zur Unterstützung unseres Teams.");
        Stellenanzeige sa3 = new Stellenanzeige();
        sa3.setJobid(2);
        sa3.setTitel("Softwareentwickler (m/w/d)");
        sa3.setBeschreibung("Wir suchen einen erfahrenen Softwareentwickler zur Unterstützung unseres Teams.");

        assertEquals(sa1.getTitel(), sa2.getTitel());  // gleiche Eigenschaften
        assertEquals(sa1.getBeschreibung(), sa2.getBeschreibung());
        assertNotEquals(sa1, sa3);  // unterschiedliche jobid
        assertEquals(sa1.hashCode(), sa2.hashCode());  // gleiche Eigenschaften -> gleicher Hashcode
        assertNotEquals(sa1.hashCode(), sa3.hashCode());  // unterschiedliche jobid -> unterschiedlicher Hashcode
    }
}
