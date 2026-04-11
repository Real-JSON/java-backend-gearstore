package com.jsko.gearstore.repository;

import com.jsko.gearstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Find a user by their email address
    Optional<User> findByEmail(String email);

    // Check if an email already exists
    boolean existsByEmail(String email);
}
