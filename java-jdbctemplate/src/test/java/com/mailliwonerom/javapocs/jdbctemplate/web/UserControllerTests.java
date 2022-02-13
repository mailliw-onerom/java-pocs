package com.mailliwonerom.javapocs.jdbctemplate.web;

import com.mailliwonerom.javapocs.jdbctemplate.domain.data.Course;
import com.mailliwonerom.javapocs.jdbctemplate.domain.data.Period;
import com.mailliwonerom.javapocs.jdbctemplate.domain.data.User;
import com.mailliwonerom.javapocs.jdbctemplate.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerTests {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private User userMock;

    @BeforeEach
    public void setUp() {
        userMock = new User("33333333333", "Paola Oliveira", Course.EVT.getCourse(),
            Period.NOCTURNAL.getPeriod());
    }

    @Test
    public void returnsSuccessAfterProccessRequest() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

        mockMvc.perform(post("/library/users")
            .content(userMock.toString())
            .contentType("application/json"))
            .andExpect(status().is(201));
    }

    @Test
    public void returnsErrorIfPayloadEqualsNull() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        mockMvc.perform(post("/library/users")
            .content(new User().toString())
            .contentType("application/json"))
            .andExpect(status().is(400));
    }

    @Test
    public void returnJsonPayloadIfIdentifierExists() throws Exception {
        mockMvc.perform(get("/library/users/{id}", "333333333"))
            .andExpect(status().is(200));
    }
}
