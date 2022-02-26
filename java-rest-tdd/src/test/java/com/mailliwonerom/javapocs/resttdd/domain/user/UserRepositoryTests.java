package com.mailliwonerom.javapocs.resttdd.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTests {

	@Autowired
	private IUserRepository userRepository;
	
	@Test
	public void readUserBasedOnHisId() throws Exception {
		Optional<User> user = userRepository.findById(1L);
		assertThat(user).isPresent();
	}
	
	@Test
	public void readUserMustReturnsOptionalEmpty() {
		Optional<User> user = userRepository.findById(2L);
		assertThat(user).isEmpty();
	}
}
