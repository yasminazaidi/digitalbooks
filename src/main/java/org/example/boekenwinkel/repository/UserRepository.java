package org.example.boekenwinkel.repository;

import org.example.boekenwinkel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Verander van findByGebruikersnaam naar findByUsername:
    Optional<User> findByUsername(String username);
}