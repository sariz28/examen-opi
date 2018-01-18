
package com.sara.examenopi.service;

import com.sara.examenopi.util.ExceptionMessage;
import java.util.List;
import org.springframework.validation.FieldError;


public interface ProcessValidFieldService {
    String processFieldErrors(List<FieldError> fieldErrors, ExceptionMessage exceptionMessage);
}
