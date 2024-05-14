package com.bsg6.chapter10;

import com.bsg6.chapter09.jpa.Artist;
import com.bsg6.chapter09.jpa.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

@SpringBootTest(classes = GatewayRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GatewayRestApplicationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSaveSongsRequiresLogin() throws Exception {
        Artist artist = new Artist("Threadbare Loaf");
        Song song = new Song(artist, "Someone Stole the Flour");
        ResponseEntity<Song> response = restTemplate.postForEntity("/songs", song, Song.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testSaveSongsWithAuth() throws Exception {
        Artist artist = new Artist("Threadbare Loaf");
        Song song = new Song(artist, "Someone Stole the Flour");
        ResponseEntity<Song> response = restTemplate.withBasicAuth("admin", "admin123").postForEntity("/songs", song, Song.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
