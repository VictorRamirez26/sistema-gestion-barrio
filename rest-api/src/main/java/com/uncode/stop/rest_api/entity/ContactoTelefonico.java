package com.uncode.stop.rest_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ContactoTelefonico extends Contacto {

    private String telefono;

    @Enumerated(EnumType.STRING)
    private TipoTelefono tipoTelefono;
}
