package com.example.application.controls.factories;

import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.entities.Keyword;
import com.example.application.entities.Stellenanzeige;
import com.example.application.entities.User;

import java.util.List;

/**
 * Klasse zur Erstellung einer neuen Stellenanzeige
 * @author sb 10.05.23
 */

public class StellenanzeigenFactory {

    public static Stellenanzeige createStellenanzeige(StellenanzeigenDTO stellenanzeigenDTO, User currentUser, List<Keyword> keywords){
        Stellenanzeige job = new Stellenanzeige();
        job.setTitel(stellenanzeigenDTO.getTitel());
        job.setBeschreibung(stellenanzeigenDTO.getBeschreibung());
        job.setUrl(stellenanzeigenDTO.getUrl());
        job.setUserid(currentUser);
        job.addKeywords(keywords);
        return job;
    }
}
