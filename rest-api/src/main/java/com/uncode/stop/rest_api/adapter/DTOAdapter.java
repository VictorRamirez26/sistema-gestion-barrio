package com.uncode.stop.rest_api.adapter;

public interface DTOAdapter<E, DTO> {

    DTO toDTO(E entity);

    E toEntity(DTO dto);

}
