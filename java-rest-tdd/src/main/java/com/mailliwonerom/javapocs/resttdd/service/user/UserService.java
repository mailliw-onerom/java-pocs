package com.mailliwonerom.javapocs.resttdd.service.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mailliwonerom.javapocs.resttdd.domain.user.IUserRepository;
import com.mailliwonerom.javapocs.resttdd.domain.user.User;

@Service
public class UserService {

	private final IUserRepository userRepository;
	
	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Optional<User> getUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		return (user.isPresent()) ? user : Optional.empty();
	}
}
