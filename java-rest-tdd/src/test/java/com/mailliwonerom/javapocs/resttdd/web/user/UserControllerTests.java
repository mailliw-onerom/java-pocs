package com.mailliwonerom.javapocs.resttdd.web.user;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.mailliwonerom.javapocs.resttdd.domain.user.User;
import com.mailliwonerom.javapocs.resttdd.service.user.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void getUserFromGivenId() throws Exception {
		when(userService.getUser(anyLong())).thenReturn(Optional.of(
			new User("Maria", "Silva")));
		
		mockMvc.perform(get("/poc/users/123"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("firstName").value("Maria"))
			.andExpect(jsonPath("lastName").value("Silva"));
	}
	
	@Test
	public void getUserForIdThatDoesNotExist() throws Exception {
		when(userService.getUser(anyLong())).thenReturn(Optional.empty());
		
		mockMvc.perform(get("/poc/users/123"))
			.andExpect(status().isNotFound());
	}
}
