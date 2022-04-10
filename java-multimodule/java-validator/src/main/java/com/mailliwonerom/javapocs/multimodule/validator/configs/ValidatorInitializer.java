package com.mailliwonerom.javapocs.multimodule.validator.configs;

import com.mailliwonerom.javapocs.multimodule.validator.domain.services.ItinValidatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class ValidatorInitializer {

    private static ItinValidatorService instance;

    public ValidatorInitializer() {}

    @Bean
    public static synchronized ItinValidatorService getValidatorInstance() {
        if(Objects.isNull(ValidatorInitializer.instance)) {
            ValidatorInitializer.instance = new ItinValidatorService();
        }
        return ValidatorInitializer.instance;
    }
}
