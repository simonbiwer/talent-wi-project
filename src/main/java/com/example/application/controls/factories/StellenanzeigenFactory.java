package com.example.application.controls.factories;

import com.example.application.controls.KeywordControl;
import com.example.application.dtos.KeywordDTO;
import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.entities.Keyword;
import com.example.application.entities.Stellenanzeige;
import com.example.application.entities.User;
import com.example.application.repositories.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
