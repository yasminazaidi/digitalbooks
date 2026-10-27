package org.example.boekenwinkel.model;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score; // Bijvoorbeeld 1 t/m 5
    private String tekst;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Review() {
    }

    public Review(int score, String tekst, User user, Book book) {
        this.score = score;
        this.tekst = tekst;
        this.user = user;
        this.book = book;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public String getTekst() { return tekst; }
    public void setTekst(String tekst) { this.tekst = tekst; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
}