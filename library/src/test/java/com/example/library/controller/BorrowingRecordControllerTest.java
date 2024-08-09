package com.example.library.controller;

import com.example.library.exception.BorrowingRecordNotFoundException;
import com.example.library.model.BorrowingRecord;
import com.example.library.service.BorrowingRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BorrowingRecordControllerTest {

    @Mock
    private BorrowingRecordService borrowingRecordService;

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    private BorrowingRecord borrowingRecord;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        borrowingRecord = new BorrowingRecord();
        borrowingRecord.setId(1L);
        // Setup other fields as needed
    }

    @Test
    @Transactional
    void testBorrowBook_Success() {
        Long bookId = 1L;
        Long patronId = 1L;

        when(borrowingRecordService.borrowBook(bookId, patronId)).thenReturn(borrowingRecord);

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.borrowBook(bookId, patronId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(borrowingRecord, response.getBody());
    }

    @Test
    @Transactional

    void testBorrowBook_BookNotFound() {
        Long bookId = 999L;
        Long patronId = 1L;

        when(borrowingRecordService.borrowBook(bookId, patronId)).thenThrow(new RuntimeException("Book not found"));

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.borrowBook(bookId, patronId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    @Transactional

    void testReturnBook_Success() {
        Long bookId = 1L;
        Long patronId = 1L;

        when(borrowingRecordService.returnBook(bookId, patronId)).thenReturn(borrowingRecord);

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.returnBook(bookId, patronId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(borrowingRecord, response.getBody());
    }

    @Test
    @Transactional

    void testReturnBook_BorrowingRecordNotFound() {
        Long bookId = 1L;
        Long patronId = 1L;

        when(borrowingRecordService.returnBook(bookId, patronId)).thenThrow(new BorrowingRecordNotFoundException(1L));

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.returnBook(bookId, patronId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetAllBorrowingRecords() {
        List<BorrowingRecord> records = new ArrayList<>();
        records.add(borrowingRecord);

        when(borrowingRecordService.getAllBorrowingRecords()).thenReturn(records);

        List<BorrowingRecord> result = borrowingRecordController.getAllBorrowingRecords();

        assertEquals(1, result.size());
        assertEquals(borrowingRecord, result.get(0));
    }

    @Test
    @Transactional(readOnly = true)

    void testGetBorrowingRecordById_Success() {
        Long id = 1L;

        when(borrowingRecordService.getBorrowingRecordById(id)).thenReturn(borrowingRecord);

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.getBorrowingRecordById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(borrowingRecord, response.getBody());
    }

    @Test
    @Transactional(readOnly = true)

    void testGetBorrowingRecordById_BorrowingRecordNotFound() {
        Long id = 999L;

        when(borrowingRecordService.getBorrowingRecordById(id)).thenThrow(new BorrowingRecordNotFoundException(id));

        ResponseEntity<BorrowingRecord> response = borrowingRecordController.getBorrowingRecordById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}
