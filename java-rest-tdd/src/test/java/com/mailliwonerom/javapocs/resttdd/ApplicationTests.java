package com.mailliwonerom.javapocs.resttdd;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import com.mailliwonerom.javapocs.resttdd.domain.user.IUserRepository;
import com.mailliwonerom.javapocs.resttdd.domain.user.User;
import com.mailliwonerom.javapocs.resttdd.service.user.UserService;
import com.mailliwonerom.javapocs.resttdd.web.user.UserController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ApplicationTests {

	@InjectMocks
	private UserController userController;
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private IUserRepository userRepository;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	void contextLoads() {
		assertThat(userController).isNotNull();
		assertThat(userService).isNotNull();
		assertThat(userRepository).isNotNull();
	}
	
	@Test
	public void integrationThatVerifyIfServiceWorksAndReturnsOk()
		throws Exception {
		
		ResponseEntity<User> user = testRestTemplate.getForEntity(
			"/poc/users/1", User.class);
		
		assertThat(user.getStatusCodeValue()).isEqualTo(200);
		assertThat(user.getHeaders().getContentType()).isEqualTo(
			MediaType.APPLICATION_JSON);
		assertThat(user.getBody().toString()).isEqualTo(
			new User(1L,"Maria","Silva").toString());
		assertThat(user.getBody().getId()).isEqualTo(1);
		assertThat(user.getBody().getFirstName()).isEqualTo("Maria");
		assertThat(user.getBody().getLastName()).isEqualTo("Silva");
	}
	
	@Test
	public void integrationThatVerifyIfServiceWorksAndReturnsNotFound()
		throws Exception {
		
		ResponseEntity<User> user = testRestTemplate.getForEntity(
			"/poc/users/2", User.class);
			
		assertThat(user.getStatusCodeValue()).isEqualTo(404);
		assertThat(user.getBody()).isEqualTo(null);
	}
}
