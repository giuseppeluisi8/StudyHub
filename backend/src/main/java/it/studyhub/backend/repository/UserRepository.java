package it.studyhub.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.studyhub.backend.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
    Optional<User> findByEmail(String email);
}
