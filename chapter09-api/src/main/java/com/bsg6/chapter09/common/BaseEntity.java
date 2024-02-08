package com.bsg6.chapter09.common;

public interface BaseEntity<ID> {
    ID getId();

    void setId(ID id);
}
