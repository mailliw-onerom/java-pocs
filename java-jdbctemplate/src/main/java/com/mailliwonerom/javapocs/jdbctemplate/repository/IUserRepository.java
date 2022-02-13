package com.mailliwonerom.javapocs.jdbctemplate.repository;

import com.mailliwonerom.javapocs.jdbctemplate.domain.data.User;

import java.util.Optional;

public interface IUserRepository {
    int createUser(User user);
    Optional<User> readUser(String id);
    int updateUser(String id, User user);
    int deleteUser(String id);
}
