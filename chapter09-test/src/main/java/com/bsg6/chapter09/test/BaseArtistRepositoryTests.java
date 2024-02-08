package com.bsg6.chapter09.test;

import com.bsg6.chapter09.common.BaseArtist;
import com.bsg6.chapter09.common.BaseArtistRepository;
import com.bsg6.chapter09.common.WildcardConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public abstract class BaseArtistRepositoryTests
        <A extends BaseArtist<ID>, ID>
        extends AbstractTestNGSpringContextTests {
    @Autowired
    BaseArtistRepository<A, ID> artistRepository;

    // to allow access to createWildcard...
    @Autowired
    WildcardConverter converter;

    protected abstract A createArtist(String name);

    @BeforeMethod
    public void clearDatabase() {
        artistRepository.deleteAll();
    }

    @Test
    public void testOperations() {
        // see if the database is empty
        assertEquals(artistRepository.count(), 0);

        var firstEntity = createArtist("Threadbare Loaf");
        var secondEntity = createArtist("Therapy Zeppelin");

        firstEntity = artistRepository.save(firstEntity);

        assertNotNull(firstEntity.getId());
        var artist = artistRepository.findById(firstEntity.getId());
        assertTrue(artist.isPresent());
        assertEquals(artist.get(), firstEntity);

        var query = artistRepository.findAllByNameIsLikeIgnoreCaseOrderByName(converter.convertToWildCard("th"));
        assertEquals(query.size(), 1l);
        assertEquals(query.get(0), firstEntity);

        artistRepository.save(secondEntity);
        query = artistRepository.findAllByNameIsLikeIgnoreCaseOrderByName(converter.convertToWildCard("th"));
        assertEquals(query.size(), 2);
    }
}
