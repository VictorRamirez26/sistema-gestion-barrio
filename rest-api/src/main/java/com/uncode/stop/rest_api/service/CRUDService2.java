package com.uncode.stop.rest_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.uncode.stop.rest_api.error.NotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CRUDService2<E extends Identifiable<ID>, ID, DTO> {

    private final JpaRepository<E, ID> repository;

    protected abstract void validate(E entity);

    protected abstract E toEntity(DTO dto);

    protected abstract E toEntity(ID id, DTO dto);

    @Transactional
    public E create(DTO dto) {
        var entity = toEntity(dto);
        entity.setId(null);
        validate(entity);
        return repository.save(entity);
    }

    public Page<E> read(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public E read(ID id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("No se encontró el recurso"));
    }

    @Transactional
    public E update(ID id, DTO dto) {
        var entity = toEntity(id, dto);
        validate(entity);
        return repository.save(entity);
    }

    @Transactional
    public void delete(ID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("No se encontró el recurso");
        }
        repository.deleteById(id);
    }

}
