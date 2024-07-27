package com.maids.libms.author;

import com.maids.libms.main.ResponseDto;
import com.maids.libms.main.service.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService extends CrudService<Author, Integer> {
    final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository jpaRepository) {
        super(jpaRepository, "Author");
        this.authorRepository = jpaRepository;
    }

    @Transactional
    public ResponseEntity<ResponseDto<Author>> testTransaction(Integer id) throws Exception {
        Author author = lookupResource(id);
        if (author.age == null) {
            author.age = 10;
        } else {
            author.age += 1;
        }
        Author entity = lookupResource(id);
        jpaRepository.save(entity);
        author.name += "e";
        throw new Exception("new exception");
    }

    public Boolean bla() {
        return true;
    }
}
