package com.sara.examenopi.dto;

public class ValidacionExcepcion extends RuntimeException{
    
    private InfoErrorDto infoError;

    public ValidacionExcepcion() {
    }

    public ValidacionExcepcion(InfoErrorDto infoError) {
        
        this.infoError = infoError;
    }

    public ValidacionExcepcion(InfoErrorDto infoError, Throwable cause) {
        super(cause);
        this.infoError = infoError;
    }

    public InfoErrorDto getInfoError() {
        return infoError;
    }

    public void setInfoError(InfoErrorDto infoError) {
        this.infoError = infoError;
    }  
    
}
