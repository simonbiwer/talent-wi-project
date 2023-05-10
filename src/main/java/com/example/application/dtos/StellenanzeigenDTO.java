package com.example.application.dtos;

import java.util.List;

/**
 * Interface eines StellenanzeigenDTO
 * @author sb 10.05.23
 */
public interface StellenanzeigenDTO {

    int getJobid();
    String getTitel();
    void setTitel(String titel);
    String getBeschreibung();
    void setBeschreibung(String beschreibung);
    String getUrl();
    void setUrl(String url);
    String getTechnologien();
    void setTechnologien(String technologien);
    String getUnternehmen();
    void setUnternehmen(String unternehmen);
    String getProjektdauer();
    void setProjektdauer(String projektdauer);
    String getStartdatum();
    void setStartdatum(String startdatum);
    String getQualifikation();
    void setQualifikation(String qualifikation);
    List<KeywordDTO> getKeywords();
    void setKeywords(List<KeywordDTO> keywords);

}
