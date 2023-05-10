package com.example.application.dtos.impl;

import com.example.application.dtos.InsertJobResult;

/**
 * Klasse, die ein Result einer Stellenanzeigen-Einspeicherung verwaltet
 * @author sb 09.05.23
 */
public class InsertJobResultImpl implements InsertJobResult {

    private String message = "";
    private boolean OK = true;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean OK() {
        return OK;
    }

    @Override
    public void setNotOK() {
        this.OK = false;
    }
}
