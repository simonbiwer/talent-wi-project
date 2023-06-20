package com.example.application.repositories;

import com.example.application.entities.Stellenanzeige;
import com.example.application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Repository-Interface für die Speicherung und Abfrage der Stellenanzeigen mit JPA
 */

@Component
@Repository
public interface StellenanzeigeRepository extends JpaRepository<Stellenanzeige, Integer> {

    Stellenanzeige findStellenanzeigeByJobid(Integer jobid);
    Stellenanzeige findStellenanzeigeByTitel(String titel);
    Stellenanzeige findStellenanzeigeByUrl(String url);
    void deleteStellenanzeigesByUserid(User userid);

}