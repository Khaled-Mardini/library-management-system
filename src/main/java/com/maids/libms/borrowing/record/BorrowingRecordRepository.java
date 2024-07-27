package com.maids.libms.borrowing.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {
    Optional<BorrowingRecord> findByPatronIdAndBookId(Integer patronId, Integer bookId);
}
