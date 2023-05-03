package com.example.application.dtos.impl;

/**
 * Klasse, die ein Ergebnis einer Registrierung verwaltet
 * @author sb
 * @since 26.04.23
 */

public class RegistrationResultDTOImpl {
    private String message = "";
    private boolean OK = true;

    public RegistrationResultDTOImpl(){
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setNotOK(){
        OK = false;
    }

    public boolean OK(){
        return OK;
    }
}
