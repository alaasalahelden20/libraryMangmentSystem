package com.example.library;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.transaction.annotation.Transactional; // Import for @Transactional

import java.time.Year;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ApplicationIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BookRepository bookRepository;

	private Book savedBook;

	@BeforeEach
	public void setUp() {
		Book book = new Book();
		book.setTitle("Integration Test Book");
		book.setAuthor("Integration Test Author");
		book.setPublicationYear(Year.of(2014));
		savedBook = bookRepository.save(book);
	}

	@AfterEach
	public void tearDown() {
		bookRepository.deleteAll();
	}

	@Test
	public void testGetAllBooks() throws Exception {
		mockMvc.perform(get("/api/books"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].title").value("Integration Test Book"))
				.andExpect(jsonPath("$[0].author").value("Integration Test Author"));
	}

	@Test
	public void testGetBookById() throws Exception {
		mockMvc.perform(get("/api/books/" + savedBook.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Integration Test Book"))
				.andExpect(jsonPath("$.author").value("Integration Test Author"));
	}

	@Test
	public void testGetBookById_NotFound() throws Exception {
		mockMvc.perform(get("/api/books/999"))
				.andExpect(status().isNotFound());
	}
}
