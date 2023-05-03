package com.example.application.dtos;

public interface LoginResultDTO {
    public boolean getResult();
    public String getReason();

    public void setResult(boolean result);
    public void setReason(String reason);
}
