package com.mailliwonerom.javapocs.net.web;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.mailliwonerom.javapocs.net.exception.header.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class HeadersTest {

    private Headers headers;

    @BeforeEach
    public void setUp() {
        headers = new Headers.Builder()
            .addHeader("Authorization",
                "bTFmNG1qM2tKZUtFcG05a25wU2lCU2FyZE04MkptWVI6ZGRVYTIydWVkNllx" +
                "VHkyVA==")
            .build();
    }

    @Test
    public void addHeaderIfThatDoesntExistsInMap() {
        assertThat(headers.get()[0]).isEqualTo("Authorization");
        assertThat(headers.get()[1]).isEqualTo(
            "bTFmNG1qM2tKZUtFcG05a25wU2lCU2FyZE04MkptWVI6ZGRVYTIydWVkNllxVHky" +
            "VA==");
    }

    @Test
    public void receiveAnExceptionIfAddedHeaderThatAlreadyExists() {
        assertThrows(HeaderKeyAlreadyExistsException.class,
            () -> new Headers.Builder()
                .addHeader("Accept", "application/json")
                .addHeader("Accept", "application/json")
                .build());
    }

    @Test
    public void removeHeaderIfExistsInHeader() {
        Headers localHeaders = new Headers.Builder()
            .addHeader("Accept", "text/plain")
            .removeHeader("Accept")
            .build();

        assertThat(localHeaders.get()).isEmpty();
    }

    @Test
    public void receiveAnExceptionIfTryRemoveHeaderThatDoesntExists() {
        assertThrows(HeaderWithoutKeyValueException.class, () ->
            new Headers.Builder()
                .addHeader("Accept", "text/plain")
                .removeHeader("Authorization"));
    }

    @Test
    public void addAllValuesPassedAsKeyValuePairs() {
    }
}
