package com.example.application.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void testGettersAndSetters() {
        User user = new User();
        user.setUserid(1);
        user.setEmail("test@example.com");
        user.setVorname("Max");
        user.setNachname("Mustermann");
        user.setPasswort("password123");

        assertEquals(1, user.getUserid());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("Max", user.getVorname());
        assertEquals("Mustermann", user.getNachname());
        assertEquals("password123", user.getPasswort());
    }

}
