package com.example.application.controls;

import com.example.application.dtos.impl.UserDTOImpl;
import com.example.application.entities.User;
import com.example.application.repositories.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


import java.util.List;
import java.util.Optional;

/*
 * Bitte nur die Klasse Komplet ausführen
 */


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class RegistrationControlTest {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  RegistrationControl registrationControl = new RegistrationControl();

    private Integer userID = null ;// User ID  fürs Löschen
    private Integer userIDEmail = null ;// User ID fürs Löschen
    private String userEmail ="max.mustermann@gmx.de";
    private String emailAlreadyInDatabase ="theo.bampis@web.de";



    @AfterAll
    public void deleteUser(){
        userRepository.deleteById(userID);

    }

    @Test
    void saveUserTest() {
        /*
         * erstelle User
         */
        UserDTOImpl user = new UserDTOImpl();
        user.setPassword( "Abc12345" );
        user.setFirstname("theo");
        user.setLastname("bampis");
        user.setEmail(userEmail);

        //User registrieren
        assertTrue(registrationControl.registerUser(user).OK());

        // User ID holen fürs Löschen (für später)
        userID = userRepository.findUserByEmail(userEmail).getUserid();

        //Vergleich, ob Wrapper Email gleich ist mit abgespeicherter Email
        Optional<User> wrapper = userRepository.findById(userID);
        if ( wrapper.isPresent() ) {
            User user1 = wrapper.get();
            assertEquals(userEmail , user1.getEmail());
        }

        /*
         * erstelle User mit gleichen daten (hier eig nur email wichtig) wie oben
         */

        UserDTOImpl userMitGleicherEmail = new UserDTOImpl();

        // Userdaten setten
        userMitGleicherEmail.setEmail(userEmail);
        userMitGleicherEmail.setPassword( "Abc12345" );
        userMitGleicherEmail.setFirstname("theo");
        userMitGleicherEmail.setLastname("bampis");

        //versuche User mit gespeicherter Email zu speicheren
        assertFalse(registrationControl.registerUser(userMitGleicherEmail).OK());
    }



    @Test
    void isEmailAlreadyInDatabase() {
        assertTrue(registrationControl.checkIfEmailOccupied(emailAlreadyInDatabase));
        assertFalse(registrationControl.checkIfEmailOccupied("dieseemail@gibts.nicht"));
    }

}