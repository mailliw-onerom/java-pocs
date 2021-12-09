package com.mailliwonerom.javapocs.net.web;

import com.mailliwonerom.javapocs.net.web.http.Headers;
import com.mailliwonerom.javapocs.net.web.http.HttpProtocol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("--- Request Maker ---")
@ExtendWith(MockitoExtension.class)
public class RequestMakerTest {

    @Mock
    private RequestMaker requestMaker;

    @Mock
    private List<HttpResponse<String>> fakeHttpResponse;

    private List<HttpRequest> fakeHttpRequest;

    @BeforeEach
    public void setUp() {
        Headers headers = new Headers.Builder()
            .add("Accept", "application/json")
            .build();

        fakeHttpRequest = List.of(
            HttpRequest.newBuilder()
                .version(HttpProtocol.VERSION_1_1.getHttpVersion())
                .GET()
                .uri(URI.create("https://api.covidtracking" +
                    ".com/v1/states/al/info.json"))
                .headers(headers.get())
                .build(),
            HttpRequest.newBuilder()
                .version(HttpProtocol.VERSION_1_1.getHttpVersion())
                .GET()
                .uri(URI.create("https://api.covidtracking" +
                    ".com/v1/states/ca/info.json"))
                .headers(headers.get())
                .build());
    }

    @Test
    public void returnsListOfHttpRequests() {
        when(requestMaker.fromList()).thenReturn(fakeHttpRequest);

        assertThat(requestMaker.fromList())
            .contains(fakeHttpRequest.get(0))
            .contains(fakeHttpRequest.get(1));
    }

    @Test
    public void receiveListOfHttpResponseWrappingJsonString() throws
        IOException, InterruptedException, TimeoutException {

        when(requestMaker.send(any())).thenReturn(fakeHttpResponse);

        assertThat(requestMaker.send(HttpClient.newHttpClient())).isEqualTo(
            fakeHttpResponse);
    }
}
