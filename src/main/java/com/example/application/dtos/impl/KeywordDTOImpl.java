package com.example.application.dtos.impl;

import com.example.application.dtos.KeywordDTO;

/**
 * Implementierung eines KeywordDTOs
 * @author sb 09.05.23
 * @since 17.05.23
 */
public class KeywordDTOImpl implements KeywordDTO {

    private int keywordid;
    private String keywordname;

    @Override
    public int getKeywordid() {
        return keywordid;
    }

    @Override
    public void setKeywordid(int keywordid){
        this.keywordid = keywordid;
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
