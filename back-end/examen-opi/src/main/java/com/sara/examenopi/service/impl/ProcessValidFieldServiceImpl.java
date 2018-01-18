/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sara.examenopi.service.impl;

import com.sara.examenopi.dto.InfoErrorDto;
import com.sara.examenopi.service.ProcessValidFieldService;
import com.sara.examenopi.util.ExceptionMessage;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

/**
 *
 * @author sara
 */
@Service
public class ProcessValidFieldServiceImpl implements ProcessValidFieldService{

    @Override
    public String processFieldErrors(List<FieldError> fieldErrors, ExceptionMessage exceptionMessage) {
        StringBuilder mensajeError = new StringBuilder();

        fieldErrors.stream()
                .map((fieldError) -> resolveLocalizedErrorMessage(fieldError, exceptionMessage))
                .forEach((String errorMessage) -> {
                 if(!mensajeError.toString().contains(errorMessage))
                        mensajeError.append(errorMessage).append(" ");
        });
        return mensajeError.toString();
    }
 
    private String resolveLocalizedErrorMessage(FieldError fieldError, ExceptionMessage exceptionMessage) {

        InfoErrorDto infoError = exceptionMessage.getMessageError(fieldError.getDefaultMessage());
        
        return infoError.getMensajeError();
    }
    
}
