package com.example.application.controls;


import com.example.application.Service.ChatGPT.ChatGPTAPIExample;
import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.entities.Stellenanzeige;
import com.example.application.repositories.StellenanzeigeRepository;
import com.example.application.utils.Globals;
import com.example.application.utils.JobInjectService;
import com.example.application.utils.SettingsService;
import com.example.application.utils.UtilNavigation;
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
    private SettingsService settingsService;

    @Autowired
    private JobInjectService jobInjectService;

    public boolean crawlDataWithChatGPT(StellenanzeigenDTO stellenanzeige){
        try{
            HashMap<String, String> result = ChatGPTAPIExample.sendRequestToChatGPT(stellenanzeige.getUrl());
            return saveAttributes(stellenanzeige, result);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private boolean saveAttributes(StellenanzeigenDTO job, HashMap<String, String> attributes){
        try{
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
            jobInjectService.setStellenanzeige(job);
            settingsService.setJobHinzufuegen(true);
            UtilNavigation.navigateToJobAdvertisementEdit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
