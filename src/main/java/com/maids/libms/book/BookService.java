package com.maids.libms.book;

import com.maids.libms.author.Author;
import com.maids.libms.author.AuthorService;
import com.maids.libms.main.ResponseDto;
import com.maids.libms.main.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends CrudService<Book, Integer> {
    @Autowired
    AuthorService authorService;

    public BookService(BookRepository jpaRepository) {
        super(jpaRepository, "Book");
    }

    @Override
    public ResponseEntity<ResponseDto<Book>> create(Book resource) {
        Author author = authorService.lookupResource(resource.authorId);
        resource.setAuthor(author);
        return super.create(resource);
    }
}
