package org.example.boekenwinkel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String isbn; // Toegevoegd voor de eisen

    private String titel;
    private String auteur;

    @Column(length = 1000)
    private String omschrijving;

    private double prijs;
    private int stock;

    // Getters en Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitel() { return titel; }
    public void setTitel(String titel) { this.titel = titel; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    public String getOmschrijving() { return omschrijving; }
    public void setOmschrijving(String omschrijving) { this.omschrijving = omschrijving; }

    public double getPrijs() { return prijs; }
    public void setPrijs(double prijs) { this.prijs = prijs; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
