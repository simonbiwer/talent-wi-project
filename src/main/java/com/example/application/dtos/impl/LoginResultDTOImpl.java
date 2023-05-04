package com.example.application.dtos.impl;

import com.example.application.dtos.LoginResultDTO;

/**

 Implementierung des LoginResultDTO-Interfaces.
 Speichert das Ergebnis des Logins und den Grund, warum dieser nicht erfolgreich war.
 @author ChatGPT
 @since 04.05.2023
 */
public class LoginResultDTOImpl implements LoginResultDTO {

    private boolean result;
    private String reason;
    /**
     * Setzt das Ergebnis des Logins.
     *
     * @param result Boolean-Wert, ob Login erfolgreich war.
     */
    @Override
    public void setResult(boolean result) {
        this.result = result;
    }

    /**
     * Setzt den Grund, warum der Login nicht erfolgreich war.
     *
     * @param reason Der Grund, warum der Login nicht erfolgreich war.
     */
    @Override
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Gibt das Ergebnis des Logins zurück.
     *
     * @return Boolean-Wert, ob Login erfolgreich war.
     */
    @Override
    public boolean getResult() {
        return this.result;
    }

    /**
     * Gibt den Grund zurück, warum der Login nicht erfolgreich war.
     *
     * @return Der Grund, warum der Login nicht erfolgreich war.
     */
    @Override
    public String getReason() {
        return this.reason;
    }

}
