package org.example.boekenwinkel.controller;

import org.example.boekenwinkel.model.Book;
import org.example.boekenwinkel.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 1. Alle boeken opvragen
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 2. Een nieuw boek toevoegen
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }
}