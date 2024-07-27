package com.maids.libms.patron;

import com.maids.libms.main.service.CrudService;
import com.maids.libms.main.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PatronService extends CrudService<Patron, Integer> {
    public PatronService(PatronRepository jpaRepository) {
        super(jpaRepository, "patron");
    }

    @Override
    public ResponseEntity<ResponseDto<Patron>> create(Patron resource) {
        resource.getContacts().forEach(contact -> contact.setPatron(resource));
        return super.create(resource);
    }
}
