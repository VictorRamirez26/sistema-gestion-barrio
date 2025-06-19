package com.uncode.stop.rest_api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.uncode.stop.rest_api.adapter.DTOAdapter;
import com.uncode.stop.rest_api.service.CrudService;
import com.uncode.stop.rest_api.service.Identifiable;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CrudController<E extends Identifiable<ID>, ID, DTO> {

    private final CrudService<E, ID> service;
    private final DTOAdapter<E, DTO> adapter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DTO create(@Valid @RequestBody DTO dto) {
        return adapter.toDTO(service.create(adapter.toEntity(dto)));
    }

    @GetMapping("/{id}")
    public DTO readOne(@PathVariable ID id) {
        return adapter.toDTO(service.readOne(id));
    }

    @GetMapping
    public Page<DTO> read(Pageable pageable) {
        return service.readAll(pageable).map(adapter::toDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DTO update(@PathVariable ID id, @Valid @RequestBody DTO dto) {
        return adapter.toDTO(service.update(id, adapter.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable ID id) {
        service.delete(id);
        return "Deleted";
    }

}
