package org.example.boekenwinkel.controller;

import org.example.boekenwinkel.model.Book;
import org.example.boekenwinkel.model.Review;
import org.example.boekenwinkel.model.User;
import org.example.boekenwinkel.repository.BookRepository;
import org.example.boekenwinkel.repository.ReviewRepository;
import org.example.boekenwinkel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    // Reviews ophalen per boek
    @GetMapping("/book/{bookId}")
    public List<Review> getReviewsByBook(@PathVariable Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    // Nieuwe review opslaan
    @PostMapping
    public ResponseEntity<?> addReview(@RequestParam Long userId,
                                       @RequestParam Long bookId,
                                       @RequestParam int score,
                                       @RequestParam String tekst) {
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);

        if (user == null || book == null) {
            return ResponseEntity.badRequest().body("Gebruiker of boek niet gevonden");
        }

        Review review = new Review(score, tekst, user, book);
        reviewRepository.save(review);
        return ResponseEntity.ok(review);
    }
}