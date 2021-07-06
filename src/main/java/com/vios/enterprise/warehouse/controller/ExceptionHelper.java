package com.vios.enterprise.warehouse.controller;

import com.vios.enterprise.warehouse.model.response.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.support.WebExchangeBindException;

public class ExceptionHelper {
    static ResponseEntity<Error> getErrorResponseEntity(WebExchangeBindException exception) {
        Error response = new Error();
        String errorMsg = "";
        StringBuilder message = new StringBuilder();

        for (final FieldError error : exception.getBindingResult().getFieldErrors()) {
            message = message.append("" + error.getDefaultMessage() + " : ").append(error.getField());
            errorMsg = message.toString();
        }
        response.setReasonCode(HttpStatus.BAD_REQUEST.name());
        response.setDescription(errorMsg);
        response.setRecoverable(false);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
