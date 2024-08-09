package com.example.library.service;

import com.example.library.model.BorrowingRecord;

import java.util.List;

public interface BorrowingRecordService {
    BorrowingRecord borrowBook(Long bookId, Long patronId);
    BorrowingRecord returnBook(Long bookId, Long patronId);
    List<BorrowingRecord> getAllBorrowingRecords();
    BorrowingRecord getBorrowingRecordById(Long id);
    // Other methods as needed
}
