package com.example.application.controls;

import com.example.application.dtos.KeywordDTO;
import com.example.application.entities.Keyword;
import com.example.application.repositories.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KeywordControl {

    @Autowired
    private KeywordRepository keywordRepo;


}
