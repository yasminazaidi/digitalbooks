package org.example.boekenwinkel.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private int aantal;
    private boolean bevestigd;
    private LocalDateTime reserveringsDatum;

    public Reservation() {
    }

    public Reservation(User user, Book book, int aantal) {
        this.user = user;
        this.book = book;
        this.aantal = aantal;
        this.bevestigd = false; // Standaard nog niet bevestigd
        this.reserveringsDatum = LocalDateTime.now();

    }
    // --- VOEG DIT TOE AAN JE RESERVATION KLASSE ---
    private String afhaalDatum;
    private String afhaalTijd;

    // Getters en setters
    public String getAfhaalDatum() {
        return afhaalDatum;
    }
    public void setAfhaalDatum(String afhaalDatum) {
        this.afhaalDatum = afhaalDatum;
    }

    public String getAfhaalTijd() {
        return afhaalTijd;
    }
    public void setAfhaalTijd(String afhaalTijd) {
        this.afhaalTijd = afhaalTijd;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public int getAantal() { return aantal; }
    public void setAantal(int aantal) { this.aantal = aantal; }

    public boolean isBevestigd() { return bevestigd; }
    public void setBevestigd(boolean bevestigd) { this.bevestigd = bevestigd; }

    public LocalDateTime getReserveringsDatum() { return reserveringsDatum; }
    public void setReserveringsDatum(LocalDateTime reserveringsDatum) { this.reserveringsDatum = reserveringsDatum; }
}