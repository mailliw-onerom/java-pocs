package com.mailliwonerom.javapocs.jdbctemplate.service;

import com.mailliwonerom.javapocs.jdbctemplate.domain.data.Course;
import com.mailliwonerom.javapocs.jdbctemplate.domain.data.Period;
import com.mailliwonerom.javapocs.jdbctemplate.domain.data.User;
import com.mailliwonerom.javapocs.jdbctemplate.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("--- UserService Tests ---")
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    public void parse_Must_Be_Returned_Boolean_True_When_Matches_Dots() {
        assertThat(userService.parse("333.444.555-55")).get()
            .isInstanceOf(Boolean.class)
            .isEqualTo(Boolean.TRUE);
    }

    @Test
    public void parse_Must_Be_Returned_Boolean_True_When_Matches_Without_Dots() {
        assertThat(userService.parse("33344455555")).get()
            .isInstanceOf(Boolean.class)
            .isEqualTo(Boolean.TRUE);
    }

    @Test
    public void parse_Must_Be_Returned_Boolean_False_When_Matches_With_Dots() {
        assertThat(userService.parse("33.344.4555-55")).get()
            .isInstanceOf(Boolean.class)
            .isEqualTo(Boolean.FALSE);
    }

    @Test
    public void parse_Must_Be_Returned_Boolean_False_When_Matches_Without_Dots() {
        assertThat(userService.parse("333444555")).get()
            .isInstanceOf(Boolean.class)
            .isEqualTo(Boolean.FALSE);
    }

    @Test
    public void individualTaxpayerReplacer_Must_Be_Returned_String_Without_Dots_And_Hyphen() {
        assertThat(userService.individualTaxpayerReplacer("333.444.555-66")).isEqualTo("33344455566");
    }

    @Test
    public void individualTaxpayerReplacer_Must_Be_Returned_Input_String_Without_Changes() {
        assertThat(userService.individualTaxpayerReplacer("33344455566")).isEqualTo("33344455566");
    }

    @Test
    public void readUser_Returns_Optional_Of_User() {
        given(userRepository.readUser("11122233344")).willReturn(Optional.ofNullable(
            new User("11122233344", "Paola Oliveira", Course.EVT.getCourse(),
                    Period.NOCTURNAL.getPeriod())));

        userService.readUser("11122233344").ifPresent(
            response -> assertThat(response)
                .hasFieldOrPropertyWithValue("individualTaxpayer", "11122233344")
                .hasFieldOrPropertyWithValue("name", "Paola Oliveira")
                .hasFieldOrPropertyWithValue("course", Course.EVT.getCourse())
                .hasFieldOrPropertyWithValue("period", Period.NOCTURNAL.getPeriod()));
    }

    @Test
    public void readUser_Returns_Optional_Of_Empty() {
        given(userRepository.readUser("11122233344")).willReturn(Optional.empty());

        assertThat(userService.readUser("11122233344").isEmpty());
    }
}

