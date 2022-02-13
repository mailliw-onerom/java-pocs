package com.mailliwonerom.javapocs.jdbctemplate.repository;

import com.mailliwonerom.javapocs.jdbctemplate.domain.data.User;
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
    public int createUser(User user) {
        return jdbcTemplate.update("INSERT INTO user(individual_taxpayer,name,course,period) VALUES (?, ?, ?, ?)",
            user.getIndividualTaxpayer(), user.getName(), user.getCourse(), user.getPeriod());
    }

    @Override
    public Optional<User> readUser(String id) {
        return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?", User.class, id));
    }

    @Override
    public int updateUser(String id, User user) {
        return jdbcTemplate.update("UPDATE user SET name = ?, course = ?, period = ? WHERE individual_taxpayer = ?",
            user.getName(), user.getCourse(), user.getPeriod(), id);
    }

    @Override
    public int deleteUser(String id) {
        return jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
    }
}
