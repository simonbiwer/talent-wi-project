package com.example.application.dtos.impl;

import com.example.application.dtos.KeywordDTO;
import com.example.application.dtos.StellenanzeigenDTO;

import java.util.List;

/**
 * Implementierung eines StellenanzeigenDTOs
 * @author sb 09.05.23
 * @since 17.05.23
 */
public class StellenanzeigenDTOImpl implements StellenanzeigenDTO {

    private int jobid;
    private String titel;
    private String beschreibung;
    private String url;
    private String technologien;
    private String unternehmen;
    private String projektdauer;
    private String startdatum;
    private String qualifikation;
    private int userid;
    private List<KeywordDTO> keywords;


    @Override
    public int getJobid() {
        return jobid;
    }

    @Override
    public void setJobid(int jobid){
        this.jobid = jobid;
    }

    @Override
    public String getTitel() {
        return titel;
    }

    @Override
    public void setTitel(String titel) {
        this.titel = titel;
    }

    @Override
    public String getBeschreibung() {
        return beschreibung;
    }

    @Override
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getTechnologien() {
        return technologien;
    }

    @Override
    public void setTechnologien(String technologien) {
        this.technologien = technologien;
    }

    @Override
    public String getUnternehmen() {
        return unternehmen;
    }

    @Override
    public void setUnternehmen(String unternehmen) {
        this.unternehmen = unternehmen;
    }

    @Override
    public String getProjektdauer() {
        return projektdauer;
    }

    @Override
    public void setProjektdauer(String projektdauer) {
        this.projektdauer = projektdauer;
    }

    @Override
    public String getStartdatum() {
        return startdatum;
    }

    @Override
    public void setStartdatum(String startdatum) {
        this.startdatum = startdatum;
    }

    @Override
    public String getQualifikation() {
        return qualifikation;
    }

    @Override
    public void setQualifikation(String qualifikation) {
        this.qualifikation = qualifikation;
    }

    @Override
    public List<KeywordDTO> getKeywords() {
        return keywords;
    }

    @Override
    public void setKeywords(List<KeywordDTO> keywords) {
        this.keywords = keywords;
    }
}
