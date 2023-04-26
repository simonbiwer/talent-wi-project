package com.example.application.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import java.time.LocalDate;


public class UserDTOTest {

    private static UserDTO benutzer;

    // Vor jedem Test eine neue Instanz von UserDTO erstellen
    @BeforeAll
    public static void setUp() {
        benutzer = new UserDTO();
    }

    // Nach jedem Test die Instanz auf null setzen
    @AfterAll
    public static void tearDown() {
        benutzer = null;
    }

    @Test
    public void testSetzeUndHoleId() {
        benutzer.setId(1);
        assertEquals(1, benutzer.getId());
    }

    @Test
    public void testSetzeUndHoleAnrede() {
        benutzer.setSalutation("Herr");
        assertEquals("Herr", benutzer.getSalutation());
    }

    @Test
    public void testSetzeUndHoleTitel() {
        benutzer.setTitle("Dr.");
        assertEquals("Dr.", benutzer.getTitle());
    }

    @Test
    public void testSetzeUndHoleVorname() {
        benutzer.setFirstname("Max");
        assertEquals("Max", benutzer.getFirstname());
    }

    @Test
    public void testSetzeUndHoleNachname() {
        benutzer.setLastname("Mustermann");
        assertEquals("Mustermann", benutzer.getLastname());
    }

    @Test
    public void testSetzeUndHoleGeburtsdatum() {
        LocalDate geburtsdatum = LocalDate.of(1990, 1, 1);
        benutzer.setDateOfBirth(geburtsdatum);
        assertEquals(geburtsdatum, benutzer.getDateOfBirth());
    }

    @Test
    public void testSetzeUndHoleEmail() {
        benutzer.setEmail("max.mustermann@example.com");
        assertEquals("max.mustermann@example.com", benutzer.getEmail());
    }

    @Test
    public void testSetzeUndHoleTelefonnummer() {
        benutzer.setPhone("+49 123456789");
        assertEquals("+49 123456789", benutzer.getPhone());
    }

    @Test
    public void testSetzeUndHolePasswort() {
        benutzer.setPassword("geheim");
        assertEquals("geheim", benutzer.getPassword());
    }

    @Test
    public void testToString() {
        benutzer.setId(1);
        benutzer.setSalutation("Herr");
        benutzer.setTitle("Dr.");
        benutzer.setFirstname("Max");
        benutzer.setLastname("Mustermann");
        benutzer.setDateOfBirth(LocalDate.of(1990, 1, 1));
        benutzer.setEmail("max.mustermann@example.com");
        benutzer.setPhone("+49 123456789");
        benutzer.setPassword("geheim");

        String erwarteteAusgabe = "id=1, salutation='Herr', title='Dr.', firstname='Max', lastname='Mustermann', dateOfBirth=1990-01-01, email='max.mustermann@example.com', phone='+49 123456789'";
        assertEquals(erwarteteAusgabe, benutzer.toString());
    }
}
