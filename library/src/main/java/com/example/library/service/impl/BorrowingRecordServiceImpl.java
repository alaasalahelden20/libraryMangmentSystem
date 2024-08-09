package com.example.library.service.impl;

import com.example.library.exception.BorrowingRecordNotFoundException;
import com.example.library.exception.BookNotFoundException;
import com.example.library.exception.PatronNotFoundException;
import com.example.library.model.BorrowingRecord;
import com.example.library.model.Book;
import com.example.library.model.Patron;
import com.example.library.repository.BorrowingRecordRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.PatronRepository;
import com.example.library.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Override
    @Transactional
    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new PatronNotFoundException(patronId));

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());

        return borrowingRecordRepository.save(borrowingRecord);
    }

    @Override
    @Transactional
    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId)
                .orElseThrow(() -> new BorrowingRecordNotFoundException(bookId, patronId));

        borrowingRecord.setReturnDate(LocalDate.now());
        return borrowingRecordRepository.save(borrowingRecord);
    }
    @Override
    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public BorrowingRecord getBorrowingRecordById(Long id) {
        return borrowingRecordRepository.findById(id)
                .orElseThrow(() -> new BorrowingRecordNotFoundException(id));
    }


    // Other methods
}
