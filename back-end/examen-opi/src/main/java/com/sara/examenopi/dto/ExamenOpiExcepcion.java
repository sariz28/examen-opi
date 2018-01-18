
package com.sara.examenopi.dto;


public class ExamenOpiExcepcion extends RuntimeException{
    
    private InfoErrorDto infoError;

    public ExamenOpiExcepcion() {
    }

    public ExamenOpiExcepcion(InfoErrorDto infoError) {
        
        this.infoError = infoError;
    }

    public ExamenOpiExcepcion(InfoErrorDto infoError, Throwable cause) {
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
