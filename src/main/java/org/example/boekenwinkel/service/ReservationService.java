package org.example.boekenwinkel.service;

import org.example.boekenwinkel.model.Book;
import org.example.boekenwinkel.model.Reservation;
import org.example.boekenwinkel.model.User;
import org.example.boekenwinkel.repository.BookRepository;
import org.example.boekenwinkel.repository.ReservationRepository;
import org.example.boekenwinkel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    // Haal alle reserveringen op voor de overzichtspagina
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Maak een nieuwe reservering aan en verlaag de voorraad
    public Reservation createReservation(Long userId, Long bookId, int aantal) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Gebruiker niet gevonden"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Boek niet gevonden"));

        if (book.getStock() < aantal) {
            throw new RuntimeException("Onvoldoende voorraad voor dit boek!");
        }

        // Verminder de stock van het boek en sla op in de database
        book.setStock(book.getStock() - aantal);
        bookRepository.save(book);

        // Maak en bewaar de reservering
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setBook(book);
        reservation.setAantal(aantal);
        reservation.setBevestigd(true); // Direct bevestigd voor de winkel

        return reservationRepository.save(reservation);
    }

    // Verwijder een reservering (en geef de stock eventueel terug)
    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservering niet gevonden"));

        // Geef de gereserveerde stock weer terug aan het boek
        Book book = reservation.getBook();
        if (book != null) {
            book.setStock(book.getStock() + reservation.getAantal());
            bookRepository.save(book);
        }

        reservationRepository.deleteById(id);
    }
}