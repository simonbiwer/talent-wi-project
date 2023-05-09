package com.example.application.controls;

import com.example.application.dtos.StellenanzeigenDTO;
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

    public boolean createStellenanzeige(StellenanzeigenDTO stellenanzeigenDTO){
        return false;
    }
}
