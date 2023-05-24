package com.example.application.utils;

import com.example.application.dtos.StellenanzeigenDTO;
import org.springframework.stereotype.Component;

/**
 * HilfsKompontente um per Dependency-Injection in Spring das StellenanzeigenDTO zu übergeben.
 *
 * @author hh
 * @since 24.05.2023
 */

@Component
public class JobInjectService {
    private StellenanzeigenDTO stellenanzeige;
    public JobInjectService(){

    }
    public StellenanzeigenDTO getStellenanzeige(){
        return stellenanzeige;
    }
    public void setStellenanzeige(StellenanzeigenDTO stellenanzeige){
        this.stellenanzeige = stellenanzeige;
    }
}
