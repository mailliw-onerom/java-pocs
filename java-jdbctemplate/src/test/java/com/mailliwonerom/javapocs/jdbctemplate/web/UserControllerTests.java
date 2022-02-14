package com.mailliwonerom.javapocs.jdbctemplate.web;

import com.mailliwonerom.javapocs.jdbctemplate.domain.data.Course;
import com.mailliwonerom.javapocs.jdbctemplate.domain.data.Period;
import com.mailliwonerom.javapocs.jdbctemplate.domain.data.User;
import com.mailliwonerom.javapocs.jdbctemplate.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@DisplayName("--- UserController Tests ---")
public class UserControllerTests {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private User userMock;

    @BeforeEach
    public void setUp() {
        userMock = new User("11122233344", "Paola Oliveira", Course.EVT.getCourse(),
            Period.NOCTURNAL.getPeriod());
    }

    @Test
    public void create_Returns_Success_After_Process_Request() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

        mockMvc.perform(post("/library/users")
            .content(userMock.toString())
            .contentType("application/json"))
            .andExpect(status().is(201));
    }

    @Test
    public void create_Returns_Error_If_Payload_Equals_Null() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        mockMvc.perform(post("/library/users")
            .content(new User().toString())
            .contentType("application/json"))
            .andExpect(status().is(400));
    }

    @Test
    public void read_Returns_Ok_If_Identifier_Exists_With_URI_Var_Without_Dots() throws Exception {
        when(userService.parse(any(String.class))).thenReturn(Optional.of(Boolean.TRUE));

        when(userService.readUser(any(String.class))).thenReturn(Optional.of(userMock));

        mockMvc.perform(get("/library/users/{id}", "11122233344"))
            .andExpect(status().is(200));
    }

    @Test
    public void read_Returns_Ok_If_Identifier_Exists_With_URI_Var_With_Dots() throws Exception {
        when(userService.parse(any(String.class))).thenReturn(Optional.of(Boolean.TRUE));

        when(userService.readUser(any(String.class))).thenReturn(Optional.of(userMock));

        mockMvc.perform(get("/library/users/{id}", "111.222.333-44"))
                .andExpect(status().is(200));
    }

    @Test
    public void read_Returns_Bad_Request_If_ID_Pattern_Doesnt_Matches() throws Exception {
        when(userService.parse(any(String.class))).thenReturn(Optional.of(Boolean.FALSE));

        mockMvc.perform(get("/library/users/{id}", "11.222.333-44"))
            .andExpect(status().is(400));
    }

    @Test
    public void read_Returns_Ok_If_ID_Pattern_Matches_And_User_Was_Found() throws Exception {
        when(userService.parse(any(String.class))).thenReturn(Optional.of(Boolean.TRUE));

        when(userService.readUser(any(String.class))).thenReturn(Optional.of(userMock));

        mockMvc.perform(get("/library/users/{id}", "111.222.333-44"))
                .andExpect(status().is(200));
    }

    @Test
    public void read_Returns_Not_Found_If_ID_Pattern_Doesnt_Matches_And_User_Wasnt_Found() throws Exception {
        when(userService.parse(any(String.class))).thenReturn(Optional.of(Boolean.TRUE));

        when(userService.readUser(any(String.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/library/users/{id}", "111.222.333-44"))
                .andExpect(status().is(404));
    }
}
