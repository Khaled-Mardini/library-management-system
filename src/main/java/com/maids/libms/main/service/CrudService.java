package com.maids.libms.main.service;

import com.maids.libms.main.BaseEntity;
import com.maids.libms.main.ResponseDto;
import com.maids.libms.main.exception.CommonExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CrudService<Entity extends BaseEntity<Id>, Id> {
    protected final JpaRepository<Entity, Id> jpaRepository;
    protected final String entityName;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public CrudService(JpaRepository<Entity, Id> jpaRepository, String entityName) {
        this.jpaRepository = jpaRepository;
        this.entityName = entityName;
    }

    public Entity lookupResource(Id id) {
        String message = "Resource: " + entityName + " with id " + id + " does not exist";
        return jpaRepository.findById(id)
                .orElseThrow(() -> new CommonExceptions.ResourceNotFoundException(message));
    }

    public ResponseEntity<ResponseDto<List<Entity>>> fetch() {
        return ResponseDto.response(jpaRepository.findAll());
    }

    public ResponseEntity<ResponseDto<Page<Entity>>> fetch(Pageable pageable) {
        return ResponseDto.response(jpaRepository.findAll(pageable));
    }

    @Cacheable(value = "pageCache", key = "'books_' + #pageable.getPageNumber()")
    public Page<Entity> getPage(Pageable pageable) {
        return jpaRepository.findAll(pageable);
    }

    public ResponseEntity<ResponseDto<Page<Entity>>> fetchCached(Pageable pageable) {
        return ResponseDto.response(getPage(pageable));
    }

    public ResponseEntity<ResponseDto<Entity>> fetch(Id id) {
        return ResponseDto.response(lookupResource(id));
    }

    public ResponseEntity<ResponseDto<Entity>> create(Entity resource) {
        log.info("created new resource " + resource.getClass());
        return ResponseDto.response(jpaRepository.save(resource), HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseDto<String>> delete(Id id) {
        Entity resource = lookupResource(id);
        jpaRepository.delete(resource);
        log.info("deleted resource " + resource.getClass());
        return ResponseDto.response("", HttpStatus.OK, "resource deleted successfully");
    }

    public ResponseEntity<ResponseDto<Entity>> update(Id id, Entity resource) {
        Entity entity = lookupResource(id);
        log.info("updated the resource " + entity.getClass());
        return ResponseDto.response(jpaRepository.save(resource));
    }
}
