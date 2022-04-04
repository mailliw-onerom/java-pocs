package com.mailliwonerom.javapocs.multimodule.gateway.domain.exceptions;

import com.mailliwonerom.javapocs.multimodule.gateway.configs.ErrorInitializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InternalServerErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InternalServerErrorException.class})
    public ResponseEntity<Object> handler(RuntimeException runtimeException,
        WebRequest webRequest) {

        return handleExceptionInternal(runtimeException, ErrorInitializer.getErrorInstance()
            .setDetail("500")
            .setMessage(runtimeException.getMessage()).orDefault("Internal Server Error")
            .build(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }
}
