package com.mailliwonerom.javapocs.net.web;

import com.mailliwonerom.javapocs.net.domain.state.AlphaCode;
import com.mailliwonerom.javapocs.net.web.http.Headers;
import com.mailliwonerom.javapocs.net.web.http.HttpProtocol;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RequestMaker {

    private List<HttpRequest> requests;
    private Headers headers;

    public RequestMaker(Headers headers) {
        this.requests = new ArrayList<>(0);
        this.headers = headers;
    }

    public List<HttpRequest> fromList() {
        for (AlphaCode code : AlphaCode.values()) {
            requests.add(HttpRequest.newBuilder()
                .version(HttpProtocol.VERSION_1_1.getHttpVersion())
                .GET()
                .uri(URI.create("https://api.covidtracking" +
                    ".com/v1/states/" + code.getKey() + "/info.json"))
                .headers(headers.get())
                .timeout(Duration.ofSeconds(55L))
                .build());
        }
        return requests;
    }

    public List<HttpResponse<String>> send(HttpClient httpClient) throws
        IOException, InterruptedException {

        List<HttpResponse<String>> responseContent = new ArrayList<>(0);

        for(HttpRequest request : fromList()) {
            responseContent.add(httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString()));
        }
        return responseContent;
    }
}
