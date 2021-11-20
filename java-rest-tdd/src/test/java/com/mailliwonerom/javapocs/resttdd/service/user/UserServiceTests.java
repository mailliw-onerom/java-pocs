package com.mailliwonerom.javapocs.resttdd.service.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mailliwonerom.javapocs.resttdd.domain.user.IUserRepository;
import com.mailliwonerom.javapocs.resttdd.domain.user.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

	@Mock
	private IUserRepository userRepository;
	
	private UserService userService;

	@BeforeEach
	public void setUp() throws Exception {
		userService = new UserService(userRepository);
	}
	
	@Test
	public void getUserBasedOnHisId() throws Exception {
		when(userRepository.findById(anyLong())).thenReturn(
			Optional.of(new User("Maria", "Silva")));
		
		assertThat(userService.getUser(123L))
			.isPresent()
			.get()
			.hasFieldOrPropertyWithValue("firstName", "Maria")
			.hasFieldOrPropertyWithValue("lastName", "Silva");
	}
	
	@Test
	public void getResponseFromUserDoesNotFound() throws Exception {
		when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		assertThat(userService.getUser(123L))
			.isEmpty();
	}
}
