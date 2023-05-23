package com.example.application.controls;

import com.example.application.controls.factories.StellenanzeigenFactory;
import com.example.application.dtos.InsertJobResult;
import com.example.application.dtos.KeywordDTO;
import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.dtos.impl.InsertJobResultImpl;
import com.example.application.dtos.impl.KeywordDTOImpl;
import com.example.application.dtos.impl.StellenanzeigenDTOImpl;
import com.example.application.entities.Keyword;
import com.example.application.entities.Stellenanzeige;
import com.example.application.entities.User;
import com.example.application.repositories.KeywordRepository;
import com.example.application.repositories.StellenanzeigeRepository;
import com.example.application.utils.UtilCurrent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Control Klasse zur Verwaltung der Stellenanzeigen im System
 * @author sb 10.05.23
 * @since 17.05.23
 */

@Component
@Transactional
public class JobControl {

    @Autowired
    private StellenanzeigeRepository jobRep;

    @Autowired
    private KeywordRepository keywordRepo;

    /**
     * Methode, die eine neue Stellenanzeige erstellt und abspeichert
     * @param stellenanzeigenDTO DTO einer neuen Stellenanzeige
     * @return ResultDTO
     */
    public InsertJobResult createStellenanzeige(StellenanzeigenDTO stellenanzeigenDTO){
        InsertJobResult result = new InsertJobResultImpl();
        if (checkIfUrlOccupied(stellenanzeigenDTO)){
            result.setMessage("Stellenanzeige mit dieser URL existiert bereits!");
            result.setNotOK();
        }
        if (result.OK()){
            // hier sollte eventuell besser mit einem UserDTO gearbeitet werden
            User currentUser = UtilCurrent.getCurrentUser();
//            User currentUser = userRep.findById(currentUserDTO.getId()).get();
            List<Keyword> keywords = saveOrReturnKeywords(stellenanzeigenDTO.getKeywords());
            Stellenanzeige newJob = StellenanzeigenFactory.createStellenanzeige(stellenanzeigenDTO, currentUser, keywords);
            try{
                jobRep.save(newJob);
            } catch (Exception e){
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
     * Methode, die alle gespeicherten Stellenanzeigen zurückgibt
     * @return Liste aller Stellenanzeigen
     */
    public List<StellenanzeigenDTO> readAllStellenanzeigen(){
        List<Stellenanzeige> jobEntities = jobRep.findAll();
        List<StellenanzeigenDTO> jobDTOs = new ArrayList<>();
        for (Stellenanzeige job : jobEntities){
            jobDTOs.add(buildJobDTO(job));
        }
        return jobDTOs;
    }

    /**
     * Methode zum Updaten einer Stellenanzeige
     * @param stellenanzeigenDTO
     * @return boolean der angibt, ob das Update erfolgreich war
     */
    public boolean updateStellenanzeige(StellenanzeigenDTO stellenanzeigenDTO){
        try{
            Optional<Stellenanzeige> optionalJob = jobRep.findById(stellenanzeigenDTO.getJobid());
            Stellenanzeige job = optionalJob.get();
            List<Keyword> keywords = saveOrReturnKeywords(stellenanzeigenDTO.getKeywords());
            Stellenanzeige updatedJob = StellenanzeigenFactory.updateStellenanzeige(job, stellenanzeigenDTO, keywords);
            jobRep.save(updatedJob);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Methode zum Löschen einer Stellenanzeige
     * @param jobid
     * @return boolean, der angibt, ob das Löschen erfolgreich war
     */
    public boolean deleteStellenanzeige(int jobid){
        try{
            jobRep.deleteById(jobid);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    /**
     * Methode, die alle Stellenanzeigen zurückgibt, die der angegebene User eingestellt hat
     * @param userid Id des aktuellen Users
     * @return Liste mit beschriebenen Stellenanzeigen
     */
    public List<StellenanzeigenDTO> getStellenanzeigenForCurrentUser(int userid){
        List<Stellenanzeige> jobEntities = jobRep.findAll();
        List<StellenanzeigenDTO> jobDTOs = new ArrayList<>();
        for (Stellenanzeige job : jobEntities){
            if (job.getUserid().getUserid() == userid){
                jobDTOs.add(buildJobDTO(job));
            }
        }
        return jobDTOs;
    }


    /**
     * Methode, die checkt ob die Stellenanzeige zu dieser URL bereits existiert
     * @param stellenanzeigenDTO neue Stellenanzeige
     * @return boolean
     */
    private boolean checkIfUrlOccupied(StellenanzeigenDTO stellenanzeigenDTO){
        return (jobRep.findStellenanzeigeByUrl(stellenanzeigenDTO.getUrl()) != null);
    }

    /**
     * Methode, die neue Keyword-Entitäten erstellt, sofern diese nicht bereits vorhanden sind
     * @param keywordDTOs Abzuspeichernde Keywords
     * @return Liste an neuen oder gefundenen Keywords
     */
    public List<Keyword> saveOrReturnKeywords(List<KeywordDTO> keywordDTOs){
        List<Keyword> keywordEntities = new ArrayList<>();
        for (KeywordDTO keywordDTO : keywordDTOs){
            Keyword keyword = keywordRepo.findKeywordByKeywordname(keywordDTO.getKeywordname());
            if (keyword != null) {
                /*Im Falle, dass das Keyword bereits in der datenbank existiert, wird kein neues keyword erzeugt,
                sondern nur der Schlüssel des existierenden Keywords mit dem neuen Job verbunden in der Verbindungstabelle.*/
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
     * Methode zur Erstellung eines DTOs aus einem Entity
     * @param job Stellenanzeigen-Entity
     * @return DTO einer Stellenanzeige
     */
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

    /**
     * Methode zur Erstellung eines KeywordDTOs aus einem Entity
     * @param keyword Keyword-Entity
     * @return KeywordDTO
     */
    private KeywordDTO buildKeywordDTO(Keyword keyword){
        KeywordDTO dto = new KeywordDTOImpl();
        dto.setKeywordid(keyword.getKeywordid());
        dto.setKeywordname(keyword.getKeywordname());
        return dto;
    }
}
