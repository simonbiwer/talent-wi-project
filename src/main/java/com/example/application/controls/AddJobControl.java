package com.example.application.controls;

import com.example.application.controls.factories.StellenanzeigenFactory;
import com.example.application.dtos.InsertJobResult;
import com.example.application.dtos.KeywordDTO;
import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.dtos.impl.InsertJobResultImpl;
import com.example.application.entities.Keyword;
import com.example.application.entities.Stellenanzeige;
import com.example.application.entities.User;
import com.example.application.repositories.KeywordRepository;
import com.example.application.repositories.StellenanzeigeRepository;
import com.example.application.utils.UtilCurrent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Control Klasse zur Eintragung einer neuen Stellenanzeige ins System
 *
 * @author ho 17.05.23
 */

@Component
public class AddJobControl {

    @Autowired
    private StellenanzeigeRepository jobRep;

    @Autowired
    private KeywordRepository keywordRepo;

    /**
     * Methode, die eine neue Stellenanzeige erstellt und abspeichert
     *
     * @param stellenanzeigenDTO DTO einer neuen Stellenanzeige
     * @return ResultDTO
     */
    public InsertJobResult createStellenanzeige(StellenanzeigenDTO stellenanzeigenDTO) {
        InsertJobResult result = new InsertJobResultImpl();
        if (checkIfUrlOccupied(stellenanzeigenDTO)) {
            result.setMessage("Stellenanzeige mit dieser URL existiert bereits!");
            result.setNotOK();
        }
        if (result.OK()) {
            // hier sollte eventuell besser mit einem UserDTO gearbeitet werden
            User currentUser = UtilCurrent.getCurrentUser();
//            User currentUser = userRep.findById(currentUserDTO.getId()).get();
            List<Keyword> keywords = saveOrReturnKeywords(stellenanzeigenDTO.getKeywords());
            Stellenanzeige newJob = StellenanzeigenFactory.createStellenanzeige(stellenanzeigenDTO, currentUser, keywords);
            try {
                jobRep.save(newJob);
            } catch (Exception e) {
                jobRep.delete(newJob);
                result.setNotOK();
                result.setMessage("Fehler beim Abspeichern in der Datenbank!");
            }
            if (result.OK()) {
                result.setMessage("Abspeicherung der Stellenanzeige erfolgreich!");
            }
        }
        return result;
    }

    /**
     * Methode, die checkt ob die Stellenanzeige zu dieser URL bereits existiert
     *
     * @param stellenanzeigenDTO neue Stellenanzeige
     * @return boolean
     */
    private boolean checkIfUrlOccupied(StellenanzeigenDTO stellenanzeigenDTO) {
        return (jobRep.findStellenanzeigeByUrl(stellenanzeigenDTO.getUrl()) != null);
    }

    /**
     * Methode, die neue Keyword-Entitäten erstellt, sofern diese nicht bereits vorhanden sind
     *
     * @param keywordDTOs Abzuspeichernde Keywords
     * @return Liste an neuen oder gefundenen Keywords
     */
    public List<Keyword> saveOrReturnKeywords(List<KeywordDTO> keywordDTOs) {
        List<Keyword> keywordEntities = new ArrayList<>();
        for (KeywordDTO keywordDTO : keywordDTOs) {
            Keyword keyword = keywordRepo.findKeywordByKeywordid(keywordDTO.getKeywordid());
            if (keyword != null) {
                keywordEntities.add(keyword);
            } else {
                Keyword keywordEntity = new Keyword();
                keywordEntity.setKeywordname(keywordDTO.getKeywordname());
                keywordRepo.save(keywordEntity);
                keywordEntities.add(keywordEntity);
            }
        }
        return keywordEntities;
    }

    /**
     * Methode, die alle Stellenanzeige zur Ausgabe anzeigt
     *
     * @return Liste an allen Stellenanzeigen
     */
    public List<Stellenanzeige> getJobs(){
         return jobRep.findAll();
    }

}
