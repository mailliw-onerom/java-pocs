package com.mailliwonerom.javapocs.multimodule.gateway.domain.exceptions;

import com.mailliwonerom.javapocs.multimodule.gateway.configs.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BadRequestHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handler(RuntimeException runtimeException,
        WebRequest webRequest) {

        return handleExceptionInternal(runtimeException, Error.getInstance()
            .setDetail("400")
            .setMessage(runtimeException.getMessage()).orDefault("Bad Request")
            .build(), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }
}
