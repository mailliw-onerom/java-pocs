package com.mailliwonerom.javapocs.net.exception.header;

public class HeaderWithoutKeyValueException extends RuntimeException {
    public HeaderWithoutKeyValueException() {}

    public HeaderWithoutKeyValueException(String message) {
        super(message);
    }
}
