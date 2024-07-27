package com.maids.libms.book;

import com.maids.libms.main.CrudController;
import com.maids.libms.main.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping("/api/books")
public class BookController extends CrudController<Book, Integer> {
    BookService bookService;
    public BookController(BookService bookService) {
        super(bookService);
        this.bookService = bookService;
    }

    private final Logger log = LoggerFactory.getLogger(BookController.class);

    @Override
    public ResponseEntity<ResponseDto<Page<Book>>> fetch(Pageable pageable) {
        return bookService.fetchCached(pageable);
    }
}
