package com.mailliwonerom.javapocs.jdbctemplate.repository;

import com.mailliwonerom.javapocs.jdbctemplate.domain.data.User;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> readUser(String id);
}
