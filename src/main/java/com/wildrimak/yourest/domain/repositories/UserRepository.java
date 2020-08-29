package com.wildrimak.yourest.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wildrimak.yourest.domain.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
}
