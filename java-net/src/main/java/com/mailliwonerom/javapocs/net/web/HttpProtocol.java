package com.mailliwonerom.javapocs.net.web;

import java.net.http.HttpClient;

public enum HttpProtocol {
    VERSION_1_1(HttpClient.Version.HTTP_1_1),
    VERSION_2(HttpClient.Version.HTTP_2);

    private HttpClient.Version httpVersion;

    HttpProtocol(HttpClient.Version httpVersion) {
        this.httpVersion = httpVersion;
    }

    public HttpClient.Version getHttpVersion() {
        return httpVersion;
    }
}
