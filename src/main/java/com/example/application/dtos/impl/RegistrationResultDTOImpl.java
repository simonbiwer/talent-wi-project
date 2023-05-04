package com.example.application.dtos.impl;

/**
 * Klasse, die ein Ergebnis einer Registrierung verwaltet
 * @author sb
 * @since 26.04.23
 */

public class RegistrationResultDTOImpl {
    private String message = "";
    private boolean OK = true;

    /**

     Konstruktor der Klasse RegistrationResultDTOImpl.
     */
    public RegistrationResultDTOImpl(){
    }

    /**

     Setzt die Nachricht des Registrierungsergebnisses.
     @param message die Nachricht des Registrierungsergebnisses
     */
    public void setMessage(String message){
        this.message = message;
    }

    /**

     Gibt die Nachricht des Registrierungsergebnisses zurück.
     @return die Nachricht des Registrierungsergebnisses
     */
    public String getMessage(){
        return message;
    }

    /**

     Setzt das Registrierungsergebnis auf "nicht OK".
     */
    public void setNotOK(){
        OK = false;
    }

    /**

     Überprüft, ob das Registrierungsergebnis "OK" ist.
     @return true, wenn das Registrierungsergebnis "OK" ist, ansonsten false
     */
    public boolean OK(){
        return OK;
    }
}