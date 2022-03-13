package com.mailliwonerom.javapocs.multimodule.validator.domain.components;

import com.mailliwonerom.javapocs.multimodule.validator.domain.services.ItinValidatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorSingleton {

    private final ItinValidatorService itinValidatorService;

    public ValidatorSingleton(ItinValidatorService itinValidatorService) {
        this.itinValidatorService = itinValidatorService;
    }

    @Bean
    public ItinValidatorService getServiceInstance() {
        return itinValidatorService;
    }
}
