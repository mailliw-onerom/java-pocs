package com.mailliwonerom.javapocs.jdbctemplate.repository;

import com.mailliwonerom.javapocs.jdbctemplate.domain.data.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

    private JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> readUser(String id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM user WHERE individual_taxpayer = ?",
                new User.Mapper(), id));
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
