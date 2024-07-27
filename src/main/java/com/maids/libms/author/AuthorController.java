package com.maids.libms.author;

import com.maids.libms.main.CrudController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
public class AuthorController extends CrudController<Author, Integer> {
    AuthorService authorService;

    public AuthorController(AuthorService crudService) {
        super(crudService);
        this.authorService = crudService;
    }

    @PutMapping("/api/test/{id}")
    public Integer testTransaction(@PathVariable Integer id) {
        try {
            authorService.testTransaction(id);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
