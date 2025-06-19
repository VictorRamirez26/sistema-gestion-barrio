package com.uncode.stop.rest_api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.uncode.stop.rest_api.service.CRUDService2;
import com.uncode.stop.rest_api.service.Identifiable;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class EntityController<E extends Identifiable<ID>, ID, DTO> {

    private final CRUDService2<E, ID, DTO> service;

    @PostMapping
    public E post(@Valid @RequestBody DTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public Page<E> get(Pageable pageable) {
        return service.read(pageable);
    }

    @GetMapping("/{id}")
    public E get(@PathVariable ID id) {
        return service.read(id);
    }

    @PutMapping("/{id}")
    public E put(@PathVariable ID id, @Valid @RequestBody DTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) {
        service.delete(id);
    }
}
