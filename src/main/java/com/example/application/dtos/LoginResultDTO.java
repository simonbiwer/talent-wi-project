package com.example.application.dtos;



/**

 Das Interface LoginResultDTO definiert Methoden, um den Ergebnisstatus eines Login-Vorgangs abzurufen und zu setzen.

 Es enthält Getter- und Setter-Methoden für den boolean-Ergebnisstatus und die Gründe, warum der Login-Vorgang fehlgeschlagen ist.

 @author ChatGPT

 @since 2021-09-01

 */

public interface LoginResultDTO {


 /**
 Gibt den Ergebnisstatus des Login-Vorgangs zurück.
 @return boolean true, wenn der Login erfolgreich war, andernfalls false
 */
 boolean getResult();

/**
 Gibt den Grund zurück, warum der Login fehlgeschlagen ist.
 @return String der Grund für den fehlgeschlagenen Login
 */
String getReason();

/**
 Setzt den Ergebnisstatus des Login-Vorgangs.
 @param result boolean der Ergebnisstatus des Login-Vorgangs (true, wenn erfolgreich, sonst false)
 */
void setResult(boolean result);

/**
 Setzt den Grund, warum der Login fehlgeschlagen ist.
 @param reason String der Grund für den fehlgeschlagenen Login
 */
void setReason(String reason);
        }
