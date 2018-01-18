package com.sara.examenopi.util;

import com.sara.examenopi.dto.InfoErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ExceptionMessage {
    
    @Autowired
    private MessageSource messageSource;
    
    public  InfoErrorDto getMessageError(String errorId){
        return  new InfoErrorDto(
                    messageSource.getMessage(errorId+".codigo", null, LocaleContextHolder.getLocale()),
                    messageSource.getMessage(errorId+".mensaje", null, LocaleContextHolder.getLocale()));
    }
    
}
