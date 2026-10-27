package org.example.boekenwinkel.controller;

import org.example.boekenwinkel.model.User;
import org.example.boekenwinkel.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1. Alle gebruikers ophalen
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 2. Nieuwe gebruiker registreren
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // 3. Gebruiker zoeken op gebruikersnaam (voor inloggen)
    @GetMapping("/find")
    public User findByUsername(@RequestParam String username) {
        return userRepository.findByGebruikersnaam(username)
                .orElseThrow(() -> new RuntimeException("Gebruiker niet gevonden"));
    }
}