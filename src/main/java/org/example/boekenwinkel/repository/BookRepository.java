package org.example.boekenwinkel.repository;

import org.example.boekenwinkel.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Handige extra methode om te zoeken op ISBN
    Optional<Book> findByIsbn(String isbn);
}