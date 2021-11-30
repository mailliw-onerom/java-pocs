package com.mailliwonerom.javapocs.net;

import com.google.gson.Gson;
import com.mailliwonerom.javapocs.net.domain.state.State;
import com.mailliwonerom.javapocs.net.domain.state.StatesWrapper;
import com.mailliwonerom.javapocs.net.web.RequestMaker;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main( String[] args ) {

        HttpClient httpClient = HttpClient.newHttpClient();
        RequestMaker requestMaker = new RequestMaker();
        List<HttpResponse<String>> responseContent = new ArrayList<>(0);

        try {
            responseContent = makeRequest(requestMaker, httpClient);
        } catch(Exception e) {
            e.printStackTrace();
        }

        if(!responseContent.isEmpty()) {
            StatesWrapper stateItems = new StatesWrapper();
            for(HttpResponse response : responseContent) {
                stateItems.addState(new Gson().fromJson(
                    response.body().toString(), State.class));
            }
            System.out.println(stateItems.toString());
        }
    }

    public static List<HttpResponse<String>> makeRequest(
        RequestMaker requestMaker, HttpClient httpClient) throws IOException,
        InterruptedException {

            List<HttpResponse<String>> responseContent = new ArrayList<>(0);

            for(HttpRequest request : requestMaker.fromList()) {
                responseContent.add(httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString()));
            }
            return responseContent;
    }
}
