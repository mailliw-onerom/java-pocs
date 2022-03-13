package com.mailliwonerom.javapocs.multimodule.gateway.domain.exceptions;

public class BadRequestException extends DomainException {
    public BadRequestException() {}

    public BadRequestException(String message) { super(message); }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
