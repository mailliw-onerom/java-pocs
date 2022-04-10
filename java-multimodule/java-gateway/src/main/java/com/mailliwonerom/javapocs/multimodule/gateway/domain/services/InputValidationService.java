package com.mailliwonerom.javapocs.multimodule.gateway.domain.services;

import com.mailliwonerom.javapocs.multimodule.gateway.domain.exceptions.BadRequestException;
import com.mailliwonerom.javapocs.multimodule.gateway.domain.exceptions.InternalServerErrorException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class InputValidationService {

    private static final Pattern ITIN = Pattern.compile(
        "^(\\d{11})|((\\d{3}\\.){2}(\\d{3}-\\d{2}))$");

    public String parse(String value) throws BadRequestException {
        return matcher(value).orElseThrow(
            () -> new BadRequestException("The value doesn't match."));
    }

    private Optional<String> matcher(String value) {
        return Optional.ofNullable(value)
            .filter(isMatching -> InputValidationService.ITIN.matcher(isMatching)
                .matches())
            .map(matched -> replacer(matched));
    }

    private String replacer(String value) {
        return Optional.ofNullable(value.trim().replaceAll("[.-]", ""))
            .orElseThrow(() -> new InternalServerErrorException(
                "ITIN punctuation replacement error."));
    }
}
