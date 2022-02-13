package com.mailliwonerom.javapocs.jdbctemplate;

import com.mailliwonerom.javapocs.jdbctemplate.web.UserController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("--- Integration Test ---")
public class ApplicationTests {

    @Test
    public void setUp() {
        assertThat(UserController.class).isNotNull();
    }
}