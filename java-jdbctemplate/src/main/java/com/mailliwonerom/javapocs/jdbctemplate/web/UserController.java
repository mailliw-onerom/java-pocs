package com.mailliwonerom.javapocs.jdbctemplate.web;

import com.mailliwonerom.javapocs.jdbctemplate.domain.data.User;
import com.mailliwonerom.javapocs.jdbctemplate.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/library")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<Optional<User>> read(@PathVariable String id) {
        return userService.readUser(id);
    }
}
