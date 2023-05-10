package com.example.application.dtos;

/**
 * Interface für ein Result einer Stellenanzeigen-Abspeicherung
 * @author sb 09.05.23
 */
public interface InsertJobResult {

    String getMessage();
    void setMessage(String message);
    boolean OK();
    void setNotOK();

}
