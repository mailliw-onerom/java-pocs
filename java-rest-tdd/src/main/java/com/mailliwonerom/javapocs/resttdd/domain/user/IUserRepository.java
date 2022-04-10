package com.mailliwonerom.javapocs.resttdd.domain.user;

import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface IUserRepository extends Repository<User, Long> {
	Optional<User> findById(Long id);
}
