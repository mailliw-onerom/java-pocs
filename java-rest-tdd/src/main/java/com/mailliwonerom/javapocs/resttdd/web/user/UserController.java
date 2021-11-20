package com.mailliwonerom.javapocs.resttdd.web.user;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mailliwonerom.javapocs.resttdd.domain.user.User;
import com.mailliwonerom.javapocs.resttdd.service.user.UserService;

@RestController
@RequestMapping("/poc")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(
		path = "/users/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		Optional<User> user = userService.getUser(id);
		if(user.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user.get(), HttpStatus.OK);
	}
}
