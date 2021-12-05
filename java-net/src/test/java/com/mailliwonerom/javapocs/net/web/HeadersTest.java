package com.mailliwonerom.javapocs.net.web;

import com.mailliwonerom.javapocs.net.web.http.Headers;
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
                "bTFmNG1qM2tKZUtFcG05a25wU2lCU2FyZE04MkptWVI6ZGRVYTIydWVkNllx" +
                "VHkyVA==")
            .build();

        assertThat(headers.get()[0]).isEqualTo("Authorization");
        assertThat(headers.get()[1]).isEqualTo(
            "bTFmNG1qM2tKZUtFcG05a25wU2lCU2FyZE04MkptWVI6ZGRVYTIydWVkNllxVHky" +
            "VA==");
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
}
