package org.example.boekenwinkel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String isbn;

    private String titel;
    private String auteur;
    private String omschrijving;
    private Double prijs;
    private Integer stock;

    // 1. Standaard lege constructor (verplicht voor JPA)
    public Book() {
    }

    // 2. Constructor met parameters zodat je testen en inserts lukken
    public Book(String isbn, String titel, String auteur, String omschrijving, Double prijs, Integer stock) {
        this.isbn = isbn;
        this.titel = titel;
        this.auteur = auteur;
        this.omschrijving = omschrijving;
        this.prijs = prijs;
        this.stock = stock;
    }

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

    public Double getPrijs() { return prijs; }
    public void setPrijs(Double prijs) { this.prijs = prijs; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}