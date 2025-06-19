package com.uncode.stop.rest_api.adapter;

import org.springframework.stereotype.Component;

@Component
public class IdentityAdapter<T> implements DTOAdapter<T, T> {

    @Override
    public T toDTO(T entity) {
        return entity;
    }

    @Override
    public T toEntity(T dto) {
        return dto;
    }

}
