package com.example.library.controller;

import com.example.library.controller.BookController;
import com.example.library.dto.BookDTO;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.BasicJsonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Year;

import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void testGetBookById() throws Exception {

        BookDTO bookDTO = new BookDTO("New Book", "New Author", Year.of(2018), "NEWISBN12345");
        Book book=new Book();
        book.setPublicationYear( bookDTO.getPublicationYear());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());

        // Mocking the service call to return a Book entity
        when(bookService.getBookById(1L)).thenReturn(book);

        // Perform the GET request and verify the response
        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Title"))
                .andExpect(jsonPath("$.author").value("Author"))
                .andExpect(jsonPath("$.publicationYear").value(2024))
                .andExpect(jsonPath("$.isbn").value("ISBN12345"));
    }



}
