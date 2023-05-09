package com.example.application.controls.factories;

import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.entities.Stellenanzeige;

public class StellenanzeigenFactory {
    public static Stellenanzeige createStellenanzeige(StellenanzeigenDTO stellenanzeigenDTO){
        Stellenanzeige job = new Stellenanzeige();
        job.setTitel(stellenanzeigenDTO.getTitel());
        job.setBeschreibung(stellenanzeigenDTO.getBeschreibung());
        job.setUrl(stellenanzeigenDTO.getUrl());
        // Job muss noch Keywords bekommen
        return job;
    }
}
