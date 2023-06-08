package com.example.application.controls.factories;

import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.entities.Keyword;
import com.example.application.entities.Stellenanzeige;
import com.example.application.entities.User;

import java.util.List;

/**
 * Klasse zur Erstellung einer neuen Stellenanzeige
 * @author sb 10.05.23
 * @lastedited hh 08.06.23
 */

public class StellenanzeigenFactory {

    public static Stellenanzeige createStellenanzeige(StellenanzeigenDTO stellenanzeigenDTO, User currentUser, List<Keyword> keywords){
        Stellenanzeige job = new Stellenanzeige();
        job.setTitel(stellenanzeigenDTO.getTitel());
        job.setBeschreibung(stellenanzeigenDTO.getBeschreibung());
        job.setUrl(stellenanzeigenDTO.getUrl());
        job.setQualifikation(stellenanzeigenDTO.getQualifikation());
        job.setProjektdauer(stellenanzeigenDTO.getProjektdauer());
        job.setStartdatum(stellenanzeigenDTO.getStartdatum());
        job.setTechnologien(stellenanzeigenDTO.getTechnologien());
        job.setUnternehmen(stellenanzeigenDTO.getUnternehmen());
        job.setReserved(stellenanzeigenDTO.getReserved());
        job.setUserid(currentUser);
        job.addKeywords(keywords);
        return job;
    }

    public static Stellenanzeige updateStellenanzeige(Stellenanzeige job, StellenanzeigenDTO jobDTO, List<Keyword> keywords){
        job.setTitel(jobDTO.getTitel());
        job.setBeschreibung(jobDTO.getBeschreibung());
        job.setUrl(jobDTO.getUrl());
        // Keywords können aktuell noch nicht wieder entfernt werden
        job.addKeywords(keywords);
        job.setProjektdauer(jobDTO.getProjektdauer());
        job.setQualifikation(jobDTO.getQualifikation());
        job.setStartdatum(jobDTO.getStartdatum());
        job.setTechnologien(jobDTO.getTechnologien());
        job.setUnternehmen(jobDTO.getUnternehmen());
        job.setReserved(jobDTO.getReserved());
        return job;
    }
}
