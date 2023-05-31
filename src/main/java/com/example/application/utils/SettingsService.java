package com.example.application.utils;

import org.springframework.stereotype.Component;

/**
 * HilfsKompontente um per Dependency-Injection in Spring das Einstellungen zu übergeben.
 *
 * @author hh
 * @since 31.05.2023
 */

@Component
public class SettingsService {
    private boolean addJob;
    public SettingsService(){

    }
    public boolean getJobHinzufuegen(){
        return addJob;
    }
    public void setJobHinzufuegen(boolean addJob){
        this.addJob = addJob;
    }
}