package com.example.bookApplication1.Service;

import com.example.bookApplication1.Entity.Book;
import com.example.bookApplication1.Repository.BookRepository;
import com.example.bookApplication1.exception.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    // Mock the BookRepository
    @Mock
    private BookRepository bookRepository;

    // Inject the BookService with mocked repository
    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBook() {
        // Given
        Book book = new Book();
        book.setId(1);
        book.setTitle("Harry Potter");
        book.setAuthor("John Doe");
        book.setGenre("Fantasy");

        // Mock the repository save method
        when(bookRepository.save(book)).thenReturn(book);

        // When
        Book savedBook = bookService.addBook(book);

        // Then
        assertEquals("Harry Potter", savedBook.getTitle());
        assertEquals("John Doe", savedBook.getAuthor());
        assertEquals("Fantasy", savedBook.getGenre());
    }

    @Test
    public void testGetBookById() {
        // Given
        Book book = new Book();
        book.setId(1);
        book.setTitle("Harry Potter");
        book.setAuthor("John Doe");
        book.setGenre("Fantasy");

        // Mock the repository findById method
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        // When
        Book foundBook = bookService.getBookById(1);

        // Then
        assertNotNull(foundBook);
        assertEquals("Harry Potter", foundBook.getTitle());
        assertEquals("John Doe", foundBook.getAuthor());
    }

    @Test
    public void testGetBookById_NotFound() {
        // Mock repository to return empty optional
        when(bookRepository.findById(1)).thenReturn(Optional.empty());

        // Then expect exception when trying to get a non-existing book
        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(1));
    }

    @Test
    public void testGetAllBooks() {
        // Given
        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("Harry Potter");
        book1.setGenre("Fantasy");

        Book book2 = new Book();
        book2.setId(2);
        book2.setTitle("The Hobbit");
        book2.setGenre("Adventure");

        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);

        // Mock the repository findAll method
        when(bookRepository.findAll()).thenReturn(bookList);

        // When
        List<Book> books = bookService.getAllBooks();

        // Then
        assertEquals(2, books.size());
        assertEquals("Harry Potter", books.get(0).getTitle());
        assertEquals("The Hobbit", books.get(1).getTitle());
    }

    @Test
    public void testUpdateBook() {
        // Given
        Book book = new Book();
        book.setId(1);
        book.setTitle("Harry Potter Updated");
        book.setAuthor("John Doe");
        book.setGenre("Fantasy");

        // Mock the repository save method
        when(bookRepository.save(book)).thenReturn(book);

        // When
        Book updatedBook = bookService.updateBook(book);

        // Then
        assertEquals("Harry Potter Updated", updatedBook.getTitle());
    }

    @Test
    public void testDeleteBook() {
        // Given
        Integer bookId = 1;

        // Mock existsById to return true
        when(bookRepository.existsById(bookId)).thenReturn(true);

        // When
        ResponseEntity<String> response = bookService.deleteBook(bookId);

        // Then
        assertEquals("Book successfully deleted", response.getBody());
        verify(bookRepository, times(1)).deleteById(bookId);  // Verify deleteById was called once
    }

    @Test
    public void testDeleteBook_NotFound() {
        // Given
        Integer bookId = 1;

        // Mock existsById to return false
        when(bookRepository.existsById(bookId)).thenReturn(false);

        // Then expect exception
        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(bookId));

        // Verify deleteById was never called
        verify(bookRepository, never()).deleteById(bookId);
    }















    @Test
    public void testFindBookById() {
        // Given
        Book book = new Book();
        book.setId(1);
        book.setTitle("Harry Potter");
        book.setAuthor("John Doe");
        book.setGenre("Fantasy");

        // Mock the repository findById method
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        // When
        Optional<Book> foundBook = Optional.ofNullable(bookService.getBookById(1));

        // Then
        assertTrue(foundBook.isPresent());
        assertEquals("Harry Potter", foundBook.get().getTitle());
        assertEquals("John Doe", foundBook.get().getAuthor());
    }


}
