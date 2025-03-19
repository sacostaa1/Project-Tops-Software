package com.dealer.carsdealer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dealer.carsdealer.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}