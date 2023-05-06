package com.example.application.repositories;

import com.example.application.entities.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
    Keyword findKeywordByKeywordid(Integer id);
    Keyword findKeywordByKeywordname(String keywordname); //Ob das funktioniert weiß ich noch nicht ~theo
}