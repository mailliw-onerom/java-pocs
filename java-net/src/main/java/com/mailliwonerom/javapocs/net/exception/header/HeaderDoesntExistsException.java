package com.mailliwonerom.javapocs.net.exception.header;

public class HeaderDoesntExistsException extends RuntimeException {
    public HeaderDoesntExistsException() {}

    public HeaderDoesntExistsException(String message) {
        super(message);
    }
}
