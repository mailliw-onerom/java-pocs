package com.mailliwonerom.javapocs.jdbctemplate.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {

    private UserRepository userRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryTests(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        userRepository = new UserRepository(jdbcTemplate);
    }

    @Test
    public void readUser_Must_Be_Returned_An_Optional_Of_User() throws Exception {
        assertThat(userRepository.readUser("11122233344")).get()
            .hasFieldOrPropertyWithValue("individualTaxpayer", "11122233344")
            .hasFieldOrPropertyWithValue("name", "Paola Oliveira")
            .hasFieldOrPropertyWithValue("course", "Events")
            .hasFieldOrPropertyWithValue("period", "Nocturnal");
    }

    @Test
    public void readUser_Must_Be_Returned_An_Optional_Of_Empty() throws Exception {
        assertThat(userRepository.readUser("22222222222")).isEmpty();
    }
}
