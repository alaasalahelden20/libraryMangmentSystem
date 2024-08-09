package com.example.library.dto;

import jakarta.validation.constraints.*;

import java.time.Year;


public class BookDTO {

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 100)
    @NotNull
    private String title;

    @NotBlank(message = "Author is required")
    @Size(min = 1, max = 100)
    @NotNull
    private String author;

    @NotNull(message = "Publication year is required")
    @NotNull
    @PastOrPresent
    private Year publicationYear;

    @Pattern(regexp = "\\d{13}", message = "ISBN must be a 13-digit number")
    @NotNull
    private String isbn;

    // Constructor with fields
    public BookDTO(String title, String author, Year publicationYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Year getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Year publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
