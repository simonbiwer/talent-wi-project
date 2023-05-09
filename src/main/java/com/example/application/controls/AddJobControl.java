package com.example.application.controls;

import com.example.application.controls.factories.StellenanzeigenFactory;
import com.example.application.dtos.InsertJobResult;
import com.example.application.dtos.StellenanzeigenDTO;
import com.example.application.dtos.impl.InsertJobResultImpl;
import com.example.application.entities.Stellenanzeige;
import com.example.application.repositories.StellenanzeigeRepository;
import com.example.application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddJobControl {

    @Autowired
    private StellenanzeigeRepository jobRep;

    @Autowired
    private UserRepository userRep;

    public InsertJobResult createStellenanzeige(StellenanzeigenDTO stellenanzeigenDTO){
        InsertJobResult result = new InsertJobResultImpl();
        if (checkIfUrlOccupied(stellenanzeigenDTO)){
            result.setMessage("Stellenanzeige mit dieser URL existiert bereits!");
            result.setNotOK();
        }
        if (result.OK()){
            Stellenanzeige newJob = StellenanzeigenFactory.createStellenanzeige(stellenanzeigenDTO);
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

    private boolean checkIfUrlOccupied(StellenanzeigenDTO stellenanzeigenDTO){
        return (jobRep.findStellenanzeigeByUrl(stellenanzeigenDTO.getUrl()) != null);
    }
}
