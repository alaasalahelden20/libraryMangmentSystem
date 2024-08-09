package com.example.library.controller;

import com.example.library.exception.BorrowingRecordNotFoundException;
import com.example.library.model.BorrowingRecord;
import com.example.library.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        try {
            BorrowingRecord borrowingRecord = borrowingRecordService.borrowBook(bookId, patronId);
            return ResponseEntity.ok(borrowingRecord);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        try {
            BorrowingRecord borrowingRecord = borrowingRecordService.returnBook(bookId, patronId);
            return ResponseEntity.ok(borrowingRecord);
        } catch (BorrowingRecordNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping
    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordService.getAllBorrowingRecords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingRecord> getBorrowingRecordById(@PathVariable Long id) {
        try {
            BorrowingRecord borrowingRecord = borrowingRecordService.getBorrowingRecordById(id);
            return ResponseEntity.ok(borrowingRecord);
        } catch (BorrowingRecordNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
