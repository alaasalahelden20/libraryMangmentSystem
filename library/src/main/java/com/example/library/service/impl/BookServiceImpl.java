package com.example.library.service.impl;

import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import for @Transactional

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    @Transactional // Ensure the operation is performed in a transaction
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional // Ensure the operation is performed in a transaction
    public Book updateBook(Long id, Book book) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPublicationYear(book.getPublicationYear());
        existingBook.setIsbn(book.getIsbn());
        return bookRepository.save(existingBook);
    }

    @Override
    @Transactional // Ensure the operation is performed in a transaction
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        bookRepository.delete(book);
    }
}
