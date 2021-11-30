package com.mailliwonerom.javapocs.net.web;

import com.mailliwonerom.javapocs.net.domain.state.AlphaCode;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

public class RequestMaker {

    private List<HttpRequest> requests;

    public RequestMaker() {
        this.requests = new ArrayList<>(0);
    }

    public List<HttpRequest> fromList() {
        for (AlphaCode code : AlphaCode.values()) {
            requests.add(HttpRequest.newBuilder()
                .version(HttpProtocol.VERSION_1_1.getHttpVersion())
                .GET()
                .uri(URI.create("https://api.covidtracking" +
                    ".com/v1/states/" + code.getKey() + "/info.json"))
                .headers("Accept", "application/json")
                .build());
        }
        return requests;
    }
}
