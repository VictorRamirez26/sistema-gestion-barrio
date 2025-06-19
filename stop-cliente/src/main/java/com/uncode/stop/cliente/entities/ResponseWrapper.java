package com.uncode.stop.cliente.entities;

import java.util.List;

import lombok.Getter;

@Getter
public class ResponseWrapper<T> {
    private List<T> content;
    private Page page;
}
