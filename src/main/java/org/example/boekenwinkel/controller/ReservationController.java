package org.example.boekenwinkel.controller;

import org.example.boekenwinkel.model.Book;
import org.example.boekenwinkel.model.Reservation;
import org.example.boekenwinkel.model.User;
import org.example.boekenwinkel.repository.BookRepository;
import org.example.boekenwinkel.repository.ReservationRepository;
import org.example.boekenwinkel.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public ReservationController(ReservationRepository reservationRepository,
                                 BookRepository bookRepository,
                                 UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    // 1. Alle reserveringen ophalen
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // 2. Een reservering aanmaken
    @PostMapping
    public Reservation createReservation(@RequestParam Long userId, @RequestParam Long bookId, @RequestParam int aantal) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Gebruiker niet gevonden"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Boek niet gevonden"));

        if (book.getStock() < aantal) {
            throw new RuntimeException("Onvoldoende stock beschikbaar!");
        }

        Reservation reservation = new Reservation(user, book, aantal);
        return reservationRepository.save(reservation);
    }

    // 3. Reservering bevestigen -> Hier vermindert de stock van de catalogus automatisch!
    @PutMapping("/{id}/confirm")
    public Reservation confirmReservation(@PathVariable Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservering niet gevonden"));

        if (!reservation.isBevestigd()) {
            Book book = reservation.getBook();
            if (book.getStock() < reservation.getAantal()) {
                throw new RuntimeException("Onvoldoende stock om de reservering te bevestigen!");
            }
            // Stock verminderen
            book.setStock(book.getStock() - reservation.getAantal());
            bookRepository.save(book);

            reservation.setBevestigd(true);
            reservationRepository.save(reservation);
        }

        return reservation;
    }
    // Reservering verwijderen / annuleren
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationRepository.deleteById(id);
    }
}