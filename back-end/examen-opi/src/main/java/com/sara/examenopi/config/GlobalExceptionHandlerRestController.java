package com.sara.examenopi.config;

import com.sara.examenopi.dto.ExamenOpiExcepcion;
import com.sara.examenopi.dto.RespuestaDto;
import com.sara.examenopi.dto.ValidacionExcepcion;;
import com.sara.examenopi.service.ProcessValidFieldService;
import com.sara.examenopi.util.ExceptionMessage;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandlerRestController {
    
    @Autowired
    private final ExceptionMessage exceptionMessage;
    private final ProcessValidFieldService processValidFieldService;
    
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandlerRestController.class);
    private final static String CODIGO_GENERICO_EXCEPCION = "500";
    
    @Autowired
    public GlobalExceptionHandlerRestController(
            ExceptionMessage exceptionMessage,
            ProcessValidFieldService processValidFieldService) {
        this.exceptionMessage = exceptionMessage;
        this.processValidFieldService = processValidFieldService;
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RespuestaDto processValidationError(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        String validationErrorMensaje= processValidFieldService.processFieldErrors(fieldErrors, exceptionMessage);

        return new RespuestaDto(validationErrorMensaje, Boolean.TRUE);
    }
    
    @ExceptionHandler(ValidacionExcepcion.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public RespuestaDto handleValidacionExcepcion(ValidacionExcepcion e) {
            return new RespuestaDto(e.getInfoError().getCodigoError(),e.getInfoError().getMensajeError(), Boolean.TRUE);
    }
    
    @ExceptionHandler(ExamenOpiExcepcion.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RespuestaDto handleExamenOpiExcepcion(ExamenOpiExcepcion e) {
            return new RespuestaDto(e.getInfoError().getCodigoError(),e.getInfoError().getMensajeError(), Boolean.TRUE);
    }
    
    @ExceptionHandler(Exception.class) 
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RespuestaDto handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new RespuestaDto(e.getMessage(), CODIGO_GENERICO_EXCEPCION, Boolean.TRUE);
    }
    
}
