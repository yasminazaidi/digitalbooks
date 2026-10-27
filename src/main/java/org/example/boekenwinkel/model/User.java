package org.example.boekenwinkel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gebruikersnaam;
    private String email;
    private String wachtwoord;

    public User() {
    }

    public User(String gebruikersnaam, String email, String wachtwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getGebruikersnaam() { return gebruikersnaam; }
    public void setGebruikersnaam(String gebruikersnaam) { this.gebruikersnaam = gebruikersnaam; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getWachtwoord() { return wachtwoord; }
    public void setWachtwoord(String wachtwoord) { this.wachtwoord = wachtwoord; }
}
