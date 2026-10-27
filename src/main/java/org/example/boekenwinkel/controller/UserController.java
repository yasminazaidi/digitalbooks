package org.example.boekenwinkel.controller;

import org.example.boekenwinkel.model.User;
import org.example.boekenwinkel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 1. Alle gebruikers ophalen
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 2. Nieuwe gebruiker registreren via JSON
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Gebruikersnaam bestaat al");
        }

        // Standaard rol instellen als deze er nog niet is
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    // 3. Gebruiker zoeken op gebruikersnaam (voor inloggen in index.html)
    @GetMapping("/find")
    public ResponseEntity<?> findByUsername(@RequestParam String username) {
        return userRepository.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().body((T) "Gebruiker niet gevonden"));
    }
}