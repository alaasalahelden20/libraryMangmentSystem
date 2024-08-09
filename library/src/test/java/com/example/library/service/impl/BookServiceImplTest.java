package com.example.library.service.impl;

import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBookByIdSuccess() {
        Book book = new Book();
        book.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetBookByIdNotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(1L));
    }
}
