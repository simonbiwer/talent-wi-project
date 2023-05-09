package com.example.application.controls.factories;

import com.example.application.dtos.KeywordDTO;
import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.entities.Keyword;
import com.example.application.entities.Stellenanzeige;

import java.util.ArrayList;
import java.util.List;

public class StellenanzeigenFactory {
    public static Stellenanzeige createStellenanzeige(StellenanzeigenDTO stellenanzeigenDTO){
        Stellenanzeige job = new Stellenanzeige();
        job.setTitel(stellenanzeigenDTO.getTitel());
        job.setBeschreibung(stellenanzeigenDTO.getBeschreibung());
        job.setUrl(stellenanzeigenDTO.getUrl());
        List<Keyword> keywords = new ArrayList<>();
        for(KeywordDTO keywordDTO : stellenanzeigenDTO.getKeywords()){
            Keyword keywordEntity = new Keyword();
            keywordEntity.setKeywordname(keywordDTO.getKeywordname());
            keywords.add(keywordEntity);
        }
        job.addKeywords(keywords);
        return job;
    }
}
