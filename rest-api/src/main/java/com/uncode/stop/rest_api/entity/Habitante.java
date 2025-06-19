package com.uncode.stop.rest_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Habitante extends Persona {

    @OneToOne
    private Inmueble inmueble;

}
