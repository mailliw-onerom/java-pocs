package com.mailliwonerom.javapocs.net.web.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("--- Headers Builder ---")
public class HeadersTest {

    @Test
    public void addOneKeyValuePairsAsHeader() {
        Headers headers = new Headers.Builder()
            .add("Authorization",
                "Basic bTFmNG1qM2tKZUtFcG05a25wU2lCU2FyZE04MkptWVI6ZGRVYTIydW" +
                    "VkNllxVHkyVA==")
            .build();

        assertThat(headers.get()[0]).isEqualTo("Authorization");
        assertThat(headers.get()[1]).isEqualTo(
            "Basic bTFmNG1qM2tKZUtFcG05a25wU2lCU2FyZE04MkptWVI6ZGRVYTIydWVkNl" +
                "lxVHkyVA==");
    }

    @Test
    public void addMultiplesKeyValuePairsUsingSameKey() {
        Headers headersI = new Headers.Builder()
                .add("Accept", "text/plain")
                .add("Accept", "application/xml")
                .add("X-Api-Key", "5Ic1LLqdhtPKItMaoVPrA4rlB6dvBUg2")
                .build();

        Headers headersII = new Headers.Builder()
                .add("Accept", "image/*")
                .add("Accept", "application/json")
                .add("X-Api-Key", "5Ic1LLqdhtPKItMaoVPrA4rlB6dvBUg2")
                .build();

        assertThat(headersI.get()[0]).isEqualTo("Accept");
        assertThat(headersI.get()[1]).isEqualTo("text/plain");
        assertThat(headersI.get()[2]).isEqualTo("Accept");
        assertThat(headersI.get()[3]).isEqualTo("application/xml");
        assertThat(headersI.get()[4]).isEqualTo("X-Api-Key");
        assertThat(headersI.get()[5]).isEqualTo(
            "5Ic1LLqdhtPKItMaoVPrA4rlB6dvBUg2");

        assertThat(headersII.get()[0]).isEqualTo("Accept");
        assertThat(headersII.get()[1]).isEqualTo("image/*");
        assertThat(headersII.get()[2]).isEqualTo("Accept");
        assertThat(headersII.get()[3]).isEqualTo("application/json");
        assertThat(headersII.get()[4]).isEqualTo("X-Api-Key");
        assertThat(headersII.get()[5]).isEqualTo(
                "5Ic1LLqdhtPKItMaoVPrA4rlB6dvBUg2");
    }

    @Test
    public void addHeadersBasedOnMaps() {
        Map<String, List<String>> headersMap = Map.of(
            "Accept", List.of("text/plain", "text/html", "application/json"));

        Headers headers = new Headers.Builder().addAll(headersMap).build();

        assertThat(headers.get()[0]).isEqualTo("Accept");
        assertThat(headers.get()[1]).isEqualTo("text/plain");
        assertThat(headers.get()[2]).isEqualTo("Accept");
        assertThat(headers.get()[3]).isEqualTo("text/html");
        assertThat(headers.get()[4]).isEqualTo("Accept");
        assertThat(headers.get()[5]).isEqualTo("application/json");
    }

    @Test
    public void addHeadersBasedOnMapsWithDuplicatedOnSameBuilderCall() {
        Map<String, List<String>> headersMapI = Map.of(
                "Accept", List.of("text/plain", "text/html", "application/json"));

        Map<String, List<String>> headersMapII = Map.of(
                "Accept", List.of("application/xhtml+xml", "image/webp"));

        Headers headers = new Headers.Builder()
            .addAll(headersMapI)
            .addAll(headersMapII)
            .build();

        assertThat(headers.get()[0]).isEqualTo("Accept");
        assertThat(headers.get()[1]).isEqualTo("text/plain");
        assertThat(headers.get()[2]).isEqualTo("Accept");
        assertThat(headers.get()[3]).isEqualTo("text/html");
        assertThat(headers.get()[4]).isEqualTo("Accept");
        assertThat(headers.get()[5]).isEqualTo("application/json");

        assertThat(headers.get()[6]).isEqualTo("Accept");
        assertThat(headers.get()[7]).isEqualTo("application/xhtml+xml");
        assertThat(headers.get()[8]).isEqualTo("Accept");
        assertThat(headers.get()[9]).isEqualTo("image/webp");
    }

    @Test
    public void getAllHeadersParsedIntoArray() {
        Headers headers = new Headers.Builder()
            .add("Accept", "application/xml")
            .add("Accept", "application/json")
            .build();

        assertThat(headers.get()).contains("Accept", "application/xml",
            "Accept", "application/json");
    }

    @Test
    public void removeItemsFromHeadersBasedOnKeyPassed() {
        Headers headers = new Headers.Builder()
            .add("Accept", "video/webm")
            .add("Accept", "video/ogg")
            .add("Accept", "video/*;q=0.9")
            .add("Accept", "application/ogg;q=0.7")
            .add("Content-Type", "application/json")
            .build();

        headers.remove("Accept");

        assertThat(headers.get()[0]).contains("Content-Type");
        assertThat(headers.get()[1]).contains("application/json");
    }

    @Test
    public void removeAllHeadersAssigned() {
        Headers headers = new Headers.Builder()
                .add("Accept", "video/webm")
                .add("Accept", "video/ogg")
                .add("Content-Type", "application/json")
                .add("X-Api-Key", "tW4biIqUPxhhfbJ5nzhxTXoCj6xiDNRg")
                .build();

        headers.removeAll();
        assertThat(headers.get()).isEmpty();
    }

    @Test
    public void parseAllEntriesFromArrayListOfHeaderTypeToStringArray() {
        Headers headers = new Headers.Builder()
            .add("Accept", "application/json")
            .add("Accept", "application/xml")
            .add("X-Api-Key", "tW4biIqUPxhhfbJ5nzhxTXoCj6xiDNRg")
            .build();

        String[] headersToArray = {
            "Accept", "application/json",
            "Accept", "application/xml",
            "X-Api-Key", "tW4biIqUPxhhfbJ5nzhxTXoCj6xiDNRg"
        };

        assertThat(headers.get()).contains(headersToArray);
    }
}