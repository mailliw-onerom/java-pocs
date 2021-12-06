package com.mailliwonerom.javapocs.net.web.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("--- Header ---")
public class HeaderTest {

    @Test
    public void addNewValueToListOfValuesBasedOnKeyAndValue() {
        Header header = new Header();
        header.add("Accept", "application/json");

        //The 'key' isn't needs be added because the header class ever uses two
        //parameters when called from main method.
        assertThat(header.getValue()).contains("application/json");
    }

    @Test
    public void addListOfValuesBasedOnSingleKey() {
        assertThat(new Header("Accept", List.of("application/json",
            "text/plain")).getValue()).contains("application/json", "text" +
                "/plain");
    }

    @Test
    public void receiveAnExceptionIfOneOrBothParametersAreEmptyOrNull() {
        Header header = new Header("Accept", List.of("image/webp"));

        assertThrows(IllegalArgumentException.class, () -> {
            header.add("", "");
            header.add("", "application/json");
            header.add("Accept", "");
            header.add(String.valueOf(null), "application/xml");
            header.add("", String.valueOf(null));
            header.add(String.valueOf(null), String.valueOf(null));
            header.add("Accept", List.of());
        });
    }

    @Test
    public void parseIfParametersArentNullOrEmpty() {
        assertThat(new Header().parse("Accept", List.of("text/plain",
            "image/webp"))).isTrue();
    }
}
