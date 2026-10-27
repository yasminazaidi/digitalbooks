package org.example.boekenwinkel.controller;

import org.example.boekenwinkel.model.Book;
import org.example.boekenwinkel.model.Review;
import org.example.boekenwinkel.model.User;
import org.example.boekenwinkel.repository.BookRepository;
import org.example.boekenwinkel.repository.ReviewRepository;
import org.example.boekenwinkel.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ReviewController(ReviewRepository reviewRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    // 1. Reviews opvragen per boek
    @GetMapping("/book/{bookId}")
    public List<Review> getReviewsByBook(@PathVariable Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    // 2. Reviews opvragen per gebruiker (voor het profiel)
    @GetMapping("/user/{userId}")
    public List<Review> getReviewsByUser(@PathVariable Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    // 3. Een nieuwe review toevoegen voor een boek
    @PostMapping
    public Review createReview(@RequestParam Long userId, @RequestParam Long bookId, @RequestParam int score, @RequestParam String tekst) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Gebruiker niet gevonden"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Boek niet gevonden"));

        Review review = new Review(score, tekst, user, book);
        return reviewRepository.save(review);
    }
}