package com.uncode.stop.rest_api.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ContactoCorreoElectronico extends Contacto {

    private String email;

}
