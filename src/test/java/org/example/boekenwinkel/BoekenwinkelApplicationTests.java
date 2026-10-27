package org.example.boekenwinkel;

import org.example.boekenwinkel.model.Book;
import org.example.boekenwinkel.model.Reservation;
import org.example.boekenwinkel.model.User;
import org.example.boekenwinkel.repository.BookRepository;
import org.example.boekenwinkel.repository.ReservationRepository;
import org.example.boekenwinkel.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoekenwinkelApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    // Test 1: Controleer of een boek correct wordt opgeslagen en opgehaald
    @Test
    void testBookCreationAndFind() {
        Book book = new Book("9789022556887", "De Alchemist", "Paulo Coelho", "Een filosofisch verhaal", 19.99, 10);
        Book savedBook = bookRepository.save(book);

        assertNotNull(savedBook.getId());
        assertEquals("De Alchemist", savedBook.getTitel());
        assertEquals(10, savedBook.getStock());
    }

    // Test 2: Controleer of een gebruiker correct wordt geregistreerd
    @Test
    void testUserRegistration() {
        User user = new User("testgebruiker", "test@email.com", "geheim123");
        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
        assertEquals("testgebruiker", savedUser.getGebruikersnaam());
    }

    // Test 3: KRITIEKE FEATURE - Controleer of de stock van het boek vermindert bij reservering/bevestiging
    @Test
    void testStockReductionOnReservationConfirmation() {
        // 1. Maak een boek met stock 5
        Book book = new Book("1234567890123", "Java Programming", "John Doe", "Leerboek", 49.99, 5);
        book = bookRepository.save(book);

        // 2. Maak een gebruiker
        User user = new User("student1", "student@ehb.be", "password");
        user = userRepository.save(user);

        // 3. Maak een reservering aan voor 2 stuks
        Reservation reservation = new Reservation(user, book, 2);
        reservation = reservationRepository.save(reservation);

        // 4. Simuleer bevestiging (stock verminderen)
        int initialStock = book.getStock();
        book.setStock(book.getStock() - reservation.getAantal());
        bookRepository.save(book);

        reservation.setBevestigd(true);
        reservationRepository.save(reservation);

        // 5. Controleer of de stock nu correct is verlaagd van 5 naar 3
        Book updatedBook = bookRepository.findById(book.getId()).orElseThrow();
        assertEquals(3, updatedBook.getStock());
        assertTrue(reservation.isBevestigd());
    }
}