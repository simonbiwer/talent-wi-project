package com.example.application.dtos.impl;

import com.example.application.dtos.LoginResultDTO;

public class LoginResultDTOImpl implements LoginResultDTO {

    private boolean result;
    private String reason;

    @Override
    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean getResult() {
        return this.result;
    }

    @Override
    public String getReason() {
        return this.reason;
    }


}
