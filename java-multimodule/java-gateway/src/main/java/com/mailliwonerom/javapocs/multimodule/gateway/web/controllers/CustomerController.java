package com.mailliwonerom.javapocs.multimodule.gateway.web.controllers;

import com.mailliwonerom.javapocs.multimodule.gateway.domain.exceptions.BadRequestException;
import com.mailliwonerom.javapocs.multimodule.gateway.domain.services.InputValidationService;
import com.mailliwonerom.javapocs.multimodule.validator.configs.ValidatorInitializer;
import com.mailliwonerom.javapocs.multimodule.validator.domain.services.ItinValidatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itin")
public class CustomerController {

    private final InputValidationService inputValidationService;
    private final ItinValidatorService itinValidatorService;

    public CustomerController(InputValidationService inputValidationService,
        ValidatorInitializer validatorInitializer) {
        this.inputValidationService = inputValidationService;
        this.itinValidatorService = ValidatorInitializer.getValidatorInstance();
    }

    @GetMapping("/customers/{value}")
    public ResponseEntity<?> validate(@PathVariable String value) {
        if(itinValidatorService.dataReceiver(inputValidationService.parse(value)))
            return new ResponseEntity<>(HttpStatus.OK);
        throw new BadRequestException("Invalid documentation number.");
    }
}
