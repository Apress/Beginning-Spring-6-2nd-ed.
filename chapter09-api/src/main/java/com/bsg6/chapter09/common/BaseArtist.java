package com.bsg6.chapter09.common;

public interface BaseArtist<ID>
    extends BaseEntity<ID> {

    /**
     * Get the artist name
     */
    String getName();

    void setName(String name);
}
