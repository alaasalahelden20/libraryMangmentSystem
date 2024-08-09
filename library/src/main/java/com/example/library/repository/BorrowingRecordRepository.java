package com.example.library.repository;

import com.example.library.model.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    @Override
    Optional<BorrowingRecord> findById(Long aLong);

    Optional<BorrowingRecord> findByBookIdAndPatronId(Long bookId, Long patronId);
}
