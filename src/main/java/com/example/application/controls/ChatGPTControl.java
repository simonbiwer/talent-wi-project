package com.example.application.controls;


import com.example.application.Service.ChatGPT.ChatGPTAPIExample;
import com.example.application.entities.Stellenanzeige;
import com.example.application.repositories.StellenanzeigeRepository;
import com.example.application.utils.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author sb 24.05.23
 * @since 24.05.23
 * Klasse zur Verknüpfung der Views und der Datenbank mit ChatGPT-Anbindung
 */

@Component
public class ChatGPTControl {

    @Autowired
    private StellenanzeigeRepository jobRepo;

    public boolean crawlDataWithChatGPT(String jobUrl){
        try{
            HashMap<String, String> result = ChatGPTAPIExample.sendRequestToChatGPT(jobUrl);
            return saveAttributes(jobUrl, result);
        } catch (Exception e){
            return false;
        }
    }

    private boolean saveAttributes(String jobUrl, HashMap<String, String> attributes){
        try{
            Stellenanzeige job = jobRepo.findStellenanzeigeByUrl(jobUrl);
            job.setTechnologien(attributes.get(Globals.Attributes.TECHNOLOGIE));
            job.setUnternehmen(attributes.get(Globals.Attributes.UNTERNEHMEN));
            if (job.getTitel() == null || job.getTitel().equals("")){
                job.setTitel(attributes.get(Globals.Attributes.TITEL));
            }
            if (job.getBeschreibung() == null || job.getBeschreibung().equals("")){
                job.setBeschreibung((attributes.get(Globals.Attributes.BESCHREIBUNG)));
            }
            job.setProjektdauer(attributes.get(Globals.Attributes.PROJEKTDAUER));
            job.setStartdatum(attributes.get(Globals.Attributes.STARTDATUM));
            job.setQualifikation(attributes.get(Globals.Attributes.QUALIFIKATIONEN));
            jobRepo.save(job);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
