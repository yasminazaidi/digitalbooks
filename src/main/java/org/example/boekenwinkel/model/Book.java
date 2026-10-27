package org.example.boekenwinkel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private String titel;
    private String auteur;
    private String omschrijving;
    private double prijs;
    private int stock;

    public Book() {
    }

    public Book(String isbn, String titel, String auteur, String omschrijving, double prijs, int stock) {
        this.isbn = isbn;
        this.titel = titel;
        this.auteur = auteur;
        this.omschrijving = omschrijving;
        this.prijs = prijs;
        this.stock = stock;
    }

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

