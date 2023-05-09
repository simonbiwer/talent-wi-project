package com.example.application.repositories;

import com.example.application.entities.Stellenanzeige;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface StellenanzeigeRepository extends JpaRepository<Stellenanzeige, Integer> {
    //Hier können noch mehr Methoden hinzugefügt werden, aber wir müssen schauen ob die alle auch klappem ~theo

    Stellenanzeige findStellenanzeigeByJobid(Integer jobid);
    Stellenanzeige findStellenanzeigeByTitel(String titel);
    Stellenanzeige findStellenanzeigeByUrl(String url);

}