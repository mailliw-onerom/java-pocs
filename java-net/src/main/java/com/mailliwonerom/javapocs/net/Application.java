package com.mailliwonerom.javapocs.net;

import com.google.gson.Gson;
import com.mailliwonerom.javapocs.net.domain.state.AlphaCode;
import com.mailliwonerom.javapocs.net.domain.state.State;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Application {

    public static void main( String[] args ) throws IOException,
        InterruptedException, IllegalArgumentException, SecurityException {

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .GET()
                .uri(URI.create("https://api.covidtracking" +
                    ".com/v1/states/"+ AlphaCode.AK.getKey() +"/info.json"))
                .headers("Accept", "application/json")
                .build();

        HttpResponse<String> responseContent = httpClient.send(httpRequest,
                HttpResponse.BodyHandlers.ofString());

        if(!responseContent.body().isEmpty()) {
            State state = new Gson().fromJson(responseContent.body(),
                State.class);
            System.out.println(state.toString());
        }
    }
}
