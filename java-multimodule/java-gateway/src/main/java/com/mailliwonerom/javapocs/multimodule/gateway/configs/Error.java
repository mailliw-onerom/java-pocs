package com.mailliwonerom.javapocs.multimodule.gateway.configs;

import com.mailliwonerom.javapocs.multimodule.gateway.domain.exceptions.ResponseWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class Error {

    private static ResponseWrapper.Fault INSTANCE;

    public Error() {}

    @Bean
    public static synchronized ResponseWrapper.Fault getInstance() {
        if(Objects.isNull(Error.INSTANCE)) {
            Error.INSTANCE = new ResponseWrapper.Fault();
        }
        return Error.INSTANCE;
    }
}
