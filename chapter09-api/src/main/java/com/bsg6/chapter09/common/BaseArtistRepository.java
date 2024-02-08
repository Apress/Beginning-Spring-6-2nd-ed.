package com.bsg6.chapter09.common;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface BaseArtistRepository
        <T extends BaseArtist<ID>, ID>
        extends CrudRepository<T, ID> {
    List<T> findAllByNameIsLikeIgnoreCaseOrderByName(String name);

    Optional<T> findByNameIgnoreCase(String name);
}
