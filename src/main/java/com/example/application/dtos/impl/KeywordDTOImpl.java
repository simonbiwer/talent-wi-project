package com.example.application.dtos.impl;

import com.example.application.dtos.KeywordDTO;

public class KeywordDTOImpl implements KeywordDTO {

    private int keywordid;
    private String keywordname;

    @Override
    public int getKeywordid() {
        return keywordid;
    }

    @Override
    public String getKeywordname() {
        return keywordname;
    }

    @Override
    public void setKeywordname(String keywordname) {
        this.keywordname = keywordname;
    }
}
