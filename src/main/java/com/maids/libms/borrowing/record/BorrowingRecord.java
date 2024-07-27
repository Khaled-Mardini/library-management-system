package com.maids.libms.borrowing.record;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maids.libms.book.Book;
import com.maids.libms.main.BaseEntity;
import com.maids.libms.patron.Patron;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Builder @Accessors(chain = true)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class BorrowingRecord extends BaseEntity<Integer> {
    @JsonIgnoreProperties(value = "borrowingRecords")
    @ManyToOne @JoinColumn(name = "patron_id")
    Patron patron;

    @JsonIgnoreProperties(value = "borrowingRecords")
    @ManyToOne @JoinColumn(name = "book_id")
    Book book;

    LocalDate returnedAt;
}
