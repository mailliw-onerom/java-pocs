package com.mailliwonerom.javapocs.jdbctemplate.web;

import com.mailliwonerom.javapocs.jdbctemplate.domain.data.User;
import com.mailliwonerom.javapocs.jdbctemplate.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> read(@PathVariable String id) {
        return userService.parse(id).get() & Boolean.TRUE
            ? userService.readUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build())
            : ResponseEntity.badRequest().build();
    }
}
