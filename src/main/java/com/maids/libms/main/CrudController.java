package com.maids.libms.main;

import com.maids.libms.main.service.CrudService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public class CrudController<Entity extends BaseEntity<Id>, Id> {
    protected final CrudService<Entity, Id> crudService;

    public CrudController(CrudService<Entity, Id> crudService) {
        this.crudService = crudService;
    }

    @GetMapping
    public ResponseEntity<ResponseDto<Page<Entity>>> fetch(Pageable pageable) {
        return crudService.fetch(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Entity>> fetch(@PathVariable Id id) {
        return crudService.fetch(id);
    }

    @PostMapping
    public ResponseEntity<ResponseDto<Entity>> create(@Valid @RequestBody Entity entity) {
        return crudService.create(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<Entity>> update(
            @PathVariable Id id,
            @Valid @RequestBody Entity entity
    ) {
        return crudService.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<String>> delete(@PathVariable Id id) {
        return crudService.delete(id);
    }
}
