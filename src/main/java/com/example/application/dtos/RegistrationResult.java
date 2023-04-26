package com.example.application.dtos;

public class RegistrationResult {
    private String message = "";
    private boolean OK = true;

    public RegistrationResult(){
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setNotOK(){
        OK = false;
    }

    public boolean OK(){
        return OK;
    }
}
