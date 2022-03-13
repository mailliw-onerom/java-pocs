package com.mailliwonerom.javapocs.multimodule.gateway.web.controllers;

import com.mailliwonerom.javapocs.multimodule.gateway.domain.services.InputValidationService;
import com.mailliwonerom.javapocs.multimodule.validator.domain.components.ValidatorSingleton;
import com.mailliwonerom.javapocs.multimodule.validator.domain.services.ItinValidatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itin")
public class CustomerController {

    private final InputValidationService validateService;
    private final ItinValidatorService itinValidatorService;

    public CustomerController(InputValidationService validateService,
                              ValidatorSingleton validatorSingleton) {
        this.validateService = validateService;
        this.itinValidatorService = validatorSingleton.getServiceInstance();
    }

    @GetMapping(path = "/customers/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> validate(@PathVariable String value) {
        itinValidatorService.dataReceiver(validateService.parse(value));
        //Return value after validating that.
        return ResponseEntity.ok().build();
    }
}
