
package com.sara.examenopi.dto;

public class RespuestaDto {
    
    private String mensajeError;
    private String codigoError;
    private boolean error;

    public RespuestaDto() {
    }

    public RespuestaDto(boolean error) {
        this.error = error;
    }
    

    public RespuestaDto(String codigoError, String mensajeError, boolean error) {
        this.codigoError = codigoError;
        this.mensajeError = mensajeError;
        this.error = error;
    }
    
    public RespuestaDto(String mensajeError, boolean error) {
        this.mensajeError = mensajeError;
        this.error = error;
    }
    
    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

}
