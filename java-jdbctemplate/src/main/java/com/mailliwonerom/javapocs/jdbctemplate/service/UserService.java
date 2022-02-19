package com.mailliwonerom.javapocs.jdbctemplate.service;

import com.mailliwonerom.javapocs.jdbctemplate.domain.data.User;
import com.mailliwonerom.javapocs.jdbctemplate.repository.IUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity createUser(User user) {
        return null;
    }

    public Optional<User> readUser(String id) {
        return userRepository.readUser(individualTaxpayerReplacer(id));
    }

    public ResponseEntity updateUser(String id, User user) {
        return null;
    }

    public ResponseEntity deleteUser(String id) {
        return null;
    }

    protected String individualTaxpayerReplacer(String input) {
        return input.replaceAll("[.-]", "");
    }

    public Optional<Boolean> parse(String id) {
        return Optional.of(Pattern.compile("^(((\\d{3}\\.){2}(\\d{3}-)(\\d{2}))|((\\d{3}){3}(\\d{2})))$")
            .matcher(id)
            .matches());
    }
}
