package com.mailliwonerom.javapocs.multimodule.validator.domain.services;

import org.springframework.stereotype.Service;

@Service
public class ItinValidatorService {

    public Boolean dataReceiver(String input) {
        System.out.println("Received Data: " + input);
        return Boolean.TRUE;
    }
}
