package com.example.fundoonotes.repository;

/*
 * User Repository
 * Handles database operations for User entity
 */



import com.fundoonotes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}