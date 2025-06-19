package com.uncode.stop.rest_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.uncode.stop.rest_api.error.NotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CrudService<E extends Identifiable<ID>, ID> implements Validator<E> {

    protected final JpaRepository<E, ID> repository;

    @Transactional
    public E create(E entity) {
        entity.setId(null);
        validate(entity);
        return repository.save(entity);
    }

    public E readOne(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró el recurso"));
    }

    public Page<E> readAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public E update(ID id, E entity) {
        entity.setId(id);
        validate(entity);
        if (repository.existsById(entity.getId())) {
            return repository.save(entity);
        } else {
            throw new NotFoundException("No se encontró el recurso");
        }
    }

    @Transactional
    public void delete(ID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("No se encontró el recurso");
        }
    }

}
