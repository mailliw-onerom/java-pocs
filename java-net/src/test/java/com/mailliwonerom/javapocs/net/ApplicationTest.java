package com.mailliwonerom.javapocs.net;

import com.google.gson.Gson;
import com.mailliwonerom.javapocs.net.domain.state.State;
import com.mailliwonerom.javapocs.net.web.RequestMaker;
import com.mailliwonerom.javapocs.net.web.http.Headers;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("--- Integrated Test ---")
public class ApplicationTest {

    private HttpClient httpClient;
    private RequestMaker requestMaker;
    private Headers headers;

    @BeforeEach
    public void setUp() {
        headers = new Headers.Builder()
            .add("Accept", "application/json")
            .build();

        httpClient = HttpClient.newHttpClient();

        requestMaker = new RequestMaker(headers);
    }

    @Test
    public void integratedRequestForJsonResponseAnalysis() {

        List<HttpResponse<String>> httpResponse = new ArrayList<>(0);
        Optional<String> fileContent = Optional.empty();

        try {
            httpResponse = requestMaker.send(httpClient);
            fileContent = fromJson();
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }

        if(!(httpResponse.isEmpty()) && !(fileContent.isEmpty())) {
            assertThat(new Gson().fromJson(httpResponse.get(0).body(),
                State.class).toString()).isEqualTo(new Gson().fromJson(
                fileContent.get(), State.class).toString());
        }
    }

    protected Optional<String> fromJson() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(
        "response.json").getFile());

        try {
            return Optional.ofNullable(FileUtils.readFileToString(file, UTF_8));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
