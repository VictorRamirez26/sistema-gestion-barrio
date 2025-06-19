package com.uncode.stop.rest_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdentifierDTO<T> {
    private T id;
}
