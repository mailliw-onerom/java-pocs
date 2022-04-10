package com.mailliwonerom.javapocs.multimodule.gateway.domain.exceptions;

public class InternalServerErrorException extends DomainException {
    public InternalServerErrorException() {}

    public InternalServerErrorException(String message) { super(message); }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
