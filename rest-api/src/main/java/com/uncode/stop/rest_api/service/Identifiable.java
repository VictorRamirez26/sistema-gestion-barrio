package com.uncode.stop.rest_api.service;

import java.io.Serializable;

public interface Identifiable<ID> extends Serializable {

    ID getId();

    void setId(ID id);

}
