package com.example.bookApplication1.Repository;

import com.example.bookApplication1.Entity.Book;
import com.example.bookApplication1.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BookRepositoryTest {

    // Mock the repository
    @Mock
    private BookRepository bookRepository;

    // Optional: Inject a service that interacts with the repository
    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBookById() {
        // Given
        Book book = new Book();
        book.setId(1);
        book.setTitle("Harry Potter");
        book.setAuthor("J.k.Rowling");
        book.setGenre("Fantasy");

        // Mock the repository behavior for findById
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        // When
        Optional<Book> foundBook = bookRepository.findById(1);

        // Then
        assertEquals("Harry Potter", foundBook.get().getTitle());
    }

    @Test
    public void testSaveBook() {
        // Given
        Book book = new Book();
        book.setTitle("Harry Potter");
        book.setAuthor("J.k.Rowling");
        book.setGenre("Fantasy");

        // Mock the repository behavior for save
        when(bookRepository.save(book)).thenReturn(book);

        // When
        Book savedBook = bookRepository.save(book);

        // Then
        assertEquals("Harry Potter", savedBook.getTitle());
        assertEquals("J.k.Rowling", savedBook.getAuthor());
        assertEquals("Fantasy", savedBook.getGenre());
    }

    @Test
    public void testDeleteBook() {
        // Given a book ID
        Integer bookId = 1;

        bookRepository.deleteById(bookId);


    }
}
