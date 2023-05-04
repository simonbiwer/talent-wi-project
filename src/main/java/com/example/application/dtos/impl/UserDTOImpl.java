package com.example.application.dtos.impl;

import com.example.application.dtos.UserDTO;

import java.time.LocalDate;

/**
 * Implementation des UserDTO zur Weitergabe von User-Daten
 * last edited: sb 01.05.23
 */

public class UserDTOImpl implements UserDTO {


    private String firstname;
    private String lastname;

    private String email;
    private int id;
    private String password;


    /**
     * Getter und setter methode
     */

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setId(int id){

    }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }


    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getEmail() { return this.email; }



    public String getPassword() { return this.password; }

    public int getId() { return this.id; }


}

