package com.example.bookApplication1.Controller;

import com.example.bookApplication1.Entity.Book;
import com.example.bookApplication1.Service.BookService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.ok(savedBook);
    }
    @GetMapping("/getBook/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Integer id) {
        
        Optional<Book> bookById = Optional.ofNullable(bookService.getBookById(id));
        return bookById.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/allBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();

        List<Book> filteredBooks = books.stream()
                .filter(book -> book.getTitle() != null)  // Only books with a title
                .collect(Collectors.toList());

        return ResponseEntity.ok(filteredBooks);
    }

    @PutMapping("/updateBook")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        Book updatedBook = bookService.updateBook(book);
        return ResponseEntity.ok(updatedBook);
    }
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") Integer id){
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
