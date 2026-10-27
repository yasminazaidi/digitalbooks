package org.example.boekenwinkel.service;

import org.example.boekenwinkel.model.Book;
import org.example.boekenwinkel.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testGetAllBooks() {
        // Arrange
        Book book1 = new Book();
        book1.setTitel("Java Programmeren");
        Book book2 = new Book();
        book2.setTitel("Spring Boot Gids");

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        // Act
        List<Book> books = bookService.getAllBooks();

        // Assert
        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetBookById() {
        // Arrange
        Book book = new Book();
        book.setId(1L);
        book.setTitel("Clean Code");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // Act
        Optional<Book> foundBook = bookService.getBookById(1L);

        // Assert
        assertTrue(foundBook.isPresent());
        assertEquals("Clean Code", foundBook.get().getTitel());
    }

    @Test
    void testSaveBook() {
        // Arrange
        Book book = new Book();
        book.setTitel("Nieuw Boek");

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // Act
        Book savedBook = bookService.saveBook(book);

        // Assert
        assertNotNull(savedBook);
        assertEquals("Nieuw Boek", savedBook.getTitel());
        verify(bookRepository, times(1)).save(book);
    }
}