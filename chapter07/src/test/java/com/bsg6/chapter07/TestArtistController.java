package com.bsg6.chapter07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestArtistController extends AbstractTestNGSpringContextTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @DataProvider
    Object[][] artistData() {
        return new Object[][]{
                new Object[]{1, "Threadbare Loaf"},
                new Object[]{2, "Therapy Zeppelin"},
                new Object[]{3, "Clancy in Silt"},
                new Object[]{-1, null},
                new Object[]{-1, "Not A Band"}

        };
    }

    @Test(dataProvider = "artistData")
    public void testGetArtist(int id, String name) {
        String url = "/artist/" + id;
        ResponseEntity<Artist> response =
                restTemplate.getForEntity(url, Artist.class);
        if (id != -1) {
            assertEquals(response.getStatusCode(), HttpStatus.OK);
            Artist artist = response.getBody();
            Artist data = new Artist(id, name);
            assertEquals(artist, data);
        } else {
            assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

    @Test(dataProvider = "artistData")
    public void testSearchForArtist(int id, String name) {
        String url = "/artist/search/" +
                (name != null ? name : "");
        ResponseEntity<Artist> response =
                restTemplate.getForEntity(url, Artist.class);
        if (name != null) {
            if (id == -1) {
                assertEquals(response.getStatusCode(),
                        HttpStatus.NOT_FOUND);
            } else {
                assertEquals(response.getStatusCode(), HttpStatus.OK);
                Artist artist = response.getBody();
                Artist data = new Artist(id, name);
                assertEquals(artist, data);
            }
        } else {
            assertEquals(response.getStatusCode(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * This method tries to save an artist that should already
     * exist in the database; this will validate that the
     * Repository's saveArtist() method returns a valid artist
     * in all cases, as it should return the original artist reference.
     */
    @Test
    public void testSaveExistingArtist() {
        String url = // "http://localhost:" + port +
                "/artist";
        Artist newArtist =
                restTemplate.getForObject(url + "/1", Artist.class);

        ResponseEntity<Artist> response =
                restTemplate.postForEntity(url, newArtist, Artist.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Artist artist = response.getBody();
        assertNotNull(artist);

        int id = artist.getId();
        assertEquals(id, newArtist.getId());
        assertEquals(artist.getName(), newArtist.getName());

        response =
                restTemplate.getForEntity(url + "/" + id, Artist.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        Artist foundArtist = response.getBody();
        assertNotNull(foundArtist);
        assertEquals(artist, foundArtist);
    }

    @DataProvider
    public Object[][] artistSearches() {
        return new Object[][]{
                new Object[]{"", 3},
                new Object[]{"T", 2},
                new Object[]{"Th", 2},
                new Object[]{"Thr", 1},
                new Object[]{"C", 1},
                new Object[]{"Z", 0}
        };
    }

    @Test(dataProvider = "artistSearches")
    public void testSearches(String name, int count) {
        // this is used to help Spring figure out what types
        // are returned by restTemplate.exchange()
        ParameterizedTypeReference<List<Artist>> type =
                new ParameterizedTypeReference<>() {
                };
        String url = "/artist/match/" + name;
        ResponseEntity<List<Artist>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                type
        );
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        List<Artist> artists = response.getBody();
        assertNotNull(artists);
        assertEquals(artists.size(), count);
    }

    /*
     * We need this to run AFTER testSearches completes, because
     * testSaveArtist() adds to the artist list and therefore we
     * might get one more artists than we're expecting out of
     * some searches.
     */
    @Test(dependsOnMethods = "testSearches")
    public void testSaveArtist() {
        String url = //"http://localhost:" + port +
                "/artist";
        Artist newArtist = new Artist(0, "The Broken Keyboards");

        ResponseEntity<Artist> response = restTemplate.postForEntity(
                url,
                newArtist,
                Artist.class
        );
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        Artist artist = response.getBody();
        assertNotNull(artist);
        int id = artist.getId();
        assertNotEquals(id, 0);
        assertEquals(artist.getName(), newArtist.getName());

        response =
                restTemplate.getForEntity(url + "/" + id, Artist.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        Artist foundArtist = response.getBody();
        assertNotNull(foundArtist);
        assertEquals(artist, foundArtist);
    }
}
