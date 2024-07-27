package com.maids.libms.author;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maids.libms.book.Book;
import com.maids.libms.main.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@Builder @Accessors(chain = true)
@NoArgsConstructor @AllArgsConstructor
public class Author extends BaseEntity<Integer> {
    @Column(nullable = false, unique = true)
    String name;

    Integer age;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "author")
    Set<Book> books = new HashSet<>();
}
