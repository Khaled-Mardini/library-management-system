package com.maids.libms.borrowing.record;

import com.maids.libms.book.Book;
import com.maids.libms.book.BookRepository;
import com.maids.libms.book.BookService;
import com.maids.libms.main.service.CrudService;
import com.maids.libms.main.ResponseDto;
import com.maids.libms.main.exception.CommonExceptions;
import com.maids.libms.patron.Patron;
import com.maids.libms.patron.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BorrowingRecordService extends CrudService<BorrowingRecord, Integer> {
    BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    BookService bookService;

    @Autowired
    PatronService patronService;

    @Autowired
    BookRepository bookRepository;

    public BorrowingRecordService(BorrowingRecordRepository borrowingRecordRepository) {
        super(borrowingRecordRepository, "borrowing record");
    }

    @Transactional
    public ResponseEntity<ResponseDto<BorrowingRecord>> borrowBook(Integer patronId, Integer bookId) {
        Patron patron = patronService.lookupResource(patronId);
        Book book = bookService.lookupResource(bookId);
        if (book.getQuantity() <= 0) {
            throw new CommonExceptions.BadRequestException("The book currently is not available");
        }
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);
        BorrowingRecord borrowingRecord = BorrowingRecord.builder()
                .book(book)
                .patron(patron)
                .build();
        return super.create(borrowingRecord);
    }

    @Transactional
    public ResponseEntity<ResponseDto<BorrowingRecord>> returnBook(Integer patronId, Integer bookId) {
        Optional<BorrowingRecord> borrowingRecord
                = borrowingRecordRepository.findByPatronIdAndBookId(patronId, bookId);
        if (borrowingRecord.isEmpty()) {
            throw new CommonExceptions.ResourceNotFoundException("The patron " + patronId +
                    " did not borrow the book " + bookId);
        }
        Book book = bookService.lookupResource(bookId);
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
        borrowingRecord.get().setReturnedAt(LocalDate.now());
        BorrowingRecord updatedBorrowingRecord = borrowingRecordRepository.save(borrowingRecord.get());
        return ResponseDto.response(updatedBorrowingRecord);
    }
}
