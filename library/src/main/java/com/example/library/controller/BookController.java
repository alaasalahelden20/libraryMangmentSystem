package com.example.library.controller;

import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import com.example.library.dto.BookDTO;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookDTO bookDTO ) {
        //convert DTO to book entity
        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublicationYear(bookDTO.getPublicationYear());
         // Save the Book entity
        Book createdBook = bookService.saveBook(book);

        // Return the saved book in the response
        return ResponseEntity.ok(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) {
        try {
            Book book = new Book();
            book.setAuthor(bookDTO.getAuthor());
            book.setTitle(bookDTO.getTitle());
            book.setIsbn(bookDTO.getIsbn());
            book.setPublicationYear(bookDTO.getPublicationYear());
            // Save the Book entity
            Book updatedBook = bookService.updateBook(id,book);
            return ResponseEntity.ok(updatedBook);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
