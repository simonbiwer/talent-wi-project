package com.example.application.controls;

import com.example.application.dtos.KeywordDTO;
import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.dtos.impl.KeywordDTOImpl;
import com.example.application.dtos.impl.StellenanzeigenDTOImpl;
import com.example.application.entities.Keyword;
import com.example.application.entities.Stellenanzeige;
import com.example.application.repositories.StellenanzeigeRepository;
import com.example.application.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Control Klasse zum Einsehen und Bearbeiten der abgespeicherten Stellenanzeigen
 * @author sb 17.05.23
 */

@Component
@Transactional
public class ShowJobControl {

    @Autowired
    private StellenanzeigeRepository stellenanzeigeRepo;

    @Autowired
    private UserRepository userRepo;

    public List<StellenanzeigenDTO> readAllStellenanzeigen(){
        List<Stellenanzeige> jobEntities = stellenanzeigeRepo.findAll();
        List<StellenanzeigenDTO> jobDTOs = new ArrayList<>();
        for (Stellenanzeige job : jobEntities){
            jobDTOs.add(buildJobDTO(job));
        }
        return jobDTOs;
    }

    private StellenanzeigenDTO buildJobDTO(Stellenanzeige job){
        StellenanzeigenDTO dto = new StellenanzeigenDTOImpl();
        dto.setJobid(job.getJobid());
        dto.setTitel(job.getTitel());
        dto.setUrl(job.getUrl());
        dto.setBeschreibung(job.getBeschreibung());
        List<KeywordDTO> keywords = new ArrayList<>();
        for (Keyword keyword : job.getKeywords()){
            keywords.add(buildKeywordDTO(keyword));
        }
        dto.setKeywords(keywords);
        dto.setProjektdauer(job.getProjektdauer());
        dto.setQualifikation(job.getQualifikation());
        dto.setStartdatum(job.getStartdatum());
        dto.setTechnologien(job.getTechnologien());
        dto.setUnternehmen(job.getUnternehmen());
        return dto;
    }

    private KeywordDTO buildKeywordDTO(Keyword keyword){
        KeywordDTO dto = new KeywordDTOImpl();
        dto.setKeywordid(keyword.getKeywordid());
        dto.setKeywordname(keyword.getKeywordname());
        return dto;
    }
}
