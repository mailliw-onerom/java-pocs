package com.mailliwonerom.javapocs.net.exception.header;

public class HeaderKeyAlreadyExistsException extends RuntimeException {
    public HeaderKeyAlreadyExistsException() {}

    public HeaderKeyAlreadyExistsException(String message) {
        super(message);
    }
}
