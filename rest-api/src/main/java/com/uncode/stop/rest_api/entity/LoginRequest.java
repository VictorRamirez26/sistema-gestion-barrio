package com.uncode.stop.rest_api.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

    private String username;
    private String password;

}
