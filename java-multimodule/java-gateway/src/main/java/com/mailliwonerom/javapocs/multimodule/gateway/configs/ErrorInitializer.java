package com.mailliwonerom.javapocs.multimodule.gateway.configs;

import com.mailliwonerom.javapocs.multimodule.gateway.domain.exceptions.ResponseWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class ErrorInitializer {

    private static ResponseWrapper.Fault instance;

    public ErrorInitializer() {}

    @Bean
    public static synchronized ResponseWrapper.Fault getErrorInstance() {
        if(Objects.isNull(ErrorInitializer.instance)) {
            ErrorInitializer.instance = new ResponseWrapper.Fault();
        }
        return ErrorInitializer.instance;
    }
}
