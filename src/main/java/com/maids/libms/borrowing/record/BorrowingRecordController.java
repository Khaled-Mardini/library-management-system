package com.maids.libms.borrowing.record;

import com.maids.libms.main.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BorrowingRecordController {
    final BorrowingRecordService borrowingRecordService;
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<ResponseDto<BorrowingRecord>> borrowBook(Integer bookId, Integer patronId){
        return borrowingRecordService.borrowBook(patronId, bookId);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<ResponseDto<BorrowingRecord>> returnBook(Integer bookId, Integer patronId){
        return borrowingRecordService.returnBook(patronId, bookId);
    }
}
