package org.example.boekenwinkel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    // --- VOEG DIT TOE ---
    // Standaard krijgt elke nieuwe gebruiker de rol "USER"
    private String role = "USER";

    // --- EN VOEG DEZE GETTER EN SETTER TOE ---
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // ... (al je bestaande getters, setters en constructors laat je hieronder gewoon staan) ...
}
