package com.maids.libms.patron;

import com.maids.libms.main.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patrons")
public class PatronController extends CrudController<Patron, Integer> {
    public PatronController(PatronService crudService) {
        super(crudService);
    }
}
