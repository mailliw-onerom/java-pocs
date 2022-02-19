package com.mailliwonerom.javapocs.jdbctemplate;

import com.mailliwonerom.javapocs.jdbctemplate.domain.data.User;
import com.mailliwonerom.javapocs.jdbctemplate.repository.UserRepository;
import com.mailliwonerom.javapocs.jdbctemplate.service.UserService;
import com.mailliwonerom.javapocs.jdbctemplate.web.UserController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("--- Integration Test ---")

public class ApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void setUp() {
        assertThat(UserController.class).isNotNull();
        assertThat(UserService.class).isNotNull();
        assertThat(UserRepository.class).isNotNull();
    }

    @Test
    public void read_Must_Be_Returned_A_Success_Response_With_Path_Variable_Without_Dots() {
        ResponseEntity<User> user = testRestTemplate.getForEntity("/library/users/{id}", User.class, "11122233344");
        assertThat(user.getStatusCode().value()).isEqualTo(200);
        assertThat(user.hasBody()).isTrue();
        assertThat(user.getBody().getIndividualTaxpayer()).isEqualTo("11122233344");
    }

    @Test
    public void read_Must_Be_Returned_A_Success_Response_With_Path_Variable_With_Dots() {
        ResponseEntity<User> user = testRestTemplate.getForEntity("/library/users/{id}", User.class, "111.222.333-44");
        assertThat(user.getStatusCode().value()).isEqualTo(200);
        assertThat(user.hasBody()).isTrue();
        assertThat(user.getBody().getIndividualTaxpayer()).isEqualTo("11122233344");
    }

    @Test
    public void read_Must_Be_Returned_A_Not_Found_Response() {
        ResponseEntity<User> user = testRestTemplate.getForEntity("/library/users/{id}", User.class, "22222222222");
        assertThat(user.getStatusCode().value()).isEqualTo(404);
        assertThat(user.hasBody()).isFalse();
    }

    @Test
    public void read_Must_Be_Returned_A_Bad_Request_Response() {
        ResponseEntity<User> user = testRestTemplate.getForEntity("/library/users/{id}", User.class, "BKDtNy3zpWJ");
        assertThat(user.getStatusCode().value()).isEqualTo(400);
    }
}