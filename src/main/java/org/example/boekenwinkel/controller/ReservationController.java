package org.example.boekenwinkel.controller;

import org.example.boekenwinkel.model.Book;
import org.example.boekenwinkel.model.Message;
import org.example.boekenwinkel.model.Reservation;
import org.example.boekenwinkel.model.User;
import org.example.boekenwinkel.repository.BookRepository;
import org.example.boekenwinkel.repository.MessageRepository;
import org.example.boekenwinkel.repository.ReservationRepository;
import org.example.boekenwinkel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestParam Long userId, @RequestParam Long bookId, @RequestParam int aantal) {
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);

        if (user == null || book == null) {
            return ResponseEntity.badRequest().body("Gebruiker of boek niet gevonden");
        }

        if (book.getStock() < aantal) {
            return ResponseEntity.badRequest().body("Onvoldoende stock beschikbaar");
        }

        // Stock verminderen
        book.setStock(book.getStock() - aantal);
        bookRepository.save(book);

        Reservation reservation = new Reservation(user, book, aantal);
        Reservation savedReservation = reservationRepository.save(reservation);

        return ResponseEntity.ok(savedReservation);
    }

    @PatchMapping("/{id}/confirm")
    public Reservation confirmReservation(@PathVariable Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservering niet gevonden"));

        reservation.setBevestigd(true);
        Reservation savedReservation = reservationRepository.save(reservation);

        // Automatisch een bericht sturen naar de gebruiker via setters (veilig voor elke Message constructor)
        if (reservation.getUser() != null && reservation.getBook() != null) {
            Message message = new Message();
            message.setRecipientUsername(reservation.getUser().getUsername());
            message.setContent("Goed nieuws! Je reservering voor het boek '" + reservation.getBook().getTitel() + "' je mag die morgen komen ophalen om 14u📚");
            messageRepository.save(message);
        }

        return savedReservation;
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationRepository.deleteById(id);
    }
}