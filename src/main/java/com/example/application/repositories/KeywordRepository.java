package com.example.application.repositories;

import com.example.application.entities.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Repository-Interface für die Speicherung und Abfrage von Keywords mit JPA
 */

@Component
@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
    Keyword findKeywordByKeywordid(Integer id);
    Keyword findKeywordByKeywordname(String keywordname);
}