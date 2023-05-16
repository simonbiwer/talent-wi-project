package com.example.application.dtos.impl;

import com.example.application.dtos.impl.UserDTOImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import java.time.LocalDate;

/**
 * Dies ist die Testklasse für die  UserDTOImpl-Klasse.
 * @author  Mhalah
 * @since 10.05.2023
 */
public class UserDTOImplTest {
    private static UserDTOImpl benutzer;

    // Vor jedem Test eine neue Instanz von UserDTOImpl erstellen
    @BeforeAll
    public static void setUp() {
        benutzer = new UserDTOImpl();
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
    public void testSetzeUndHoleEmail() {
        benutzer.setEmail("max.mustermann@example.com");
        assertEquals("max.mustermann@example.com", benutzer.getEmail());
    }

    @Test
    public void testSetzeUndHolePasswort() {
        benutzer.setPassword("geheim");
        assertEquals("geheim", benutzer.getPassword());
    }

    @Test
    public void testToString() {
        benutzer.setId(1);
        benutzer.setFirstname("Max");
        benutzer.setLastname("Mustermann");
        benutzer.setEmail("max.mustermann@example.com");
        benutzer.setPassword("geheim");

        String erwarteteAusgabe = "firstname='Max', lastname='Mustermann', email='max.mustermann@example.com', id=1, password='geheim'";
        assertEquals(erwarteteAusgabe, benutzer.toString());
    }

}
