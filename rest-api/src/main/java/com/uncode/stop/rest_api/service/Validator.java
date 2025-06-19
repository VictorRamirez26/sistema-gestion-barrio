package com.uncode.stop.rest_api.service;

public interface Validator<T> {

    public void validate(T entity);

}
