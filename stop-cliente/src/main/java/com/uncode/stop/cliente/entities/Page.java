package com.uncode.stop.cliente.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {
    private int size;
    private int number;
    private int totalElements;
    private int totalPages;
}
