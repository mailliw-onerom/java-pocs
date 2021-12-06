package com.mailliwonerom.javapocs.net.web.http;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("--- Header ---")
public class HeaderTest {

    @Test
    public void addNewValueToListOfValuesBasedOnKeyAndValue() {
        Header header = new Header();
        header.add("Accept", "application/json");

        //The 'key' isn't needs be added because the header class ever uses two
        //parameters.
        assertThat(header.getValue()).contains("application/json");
    }

    @Test
    public void receiveAnExceptionIfOneOrBothParametersAreEmptyOrNull() {
        Header header = new Header();

        assertThrows(IllegalArgumentException.class, () -> {
            header.add("", "");
            header.add("", "application/json");
            header.add("Accept", "");
            header.add(String.valueOf(null), "application/json");
            header.add("", String.valueOf(null));
            header.add(String.valueOf(null), String.valueOf(null));
        });
    }
}
