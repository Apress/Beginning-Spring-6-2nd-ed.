package com.bsg6.chapter11.artist;

import com.bsg6.chapter11.artist.internal.Artist;
import com.bsg6.chapter11.artist.internal.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository repository;

    public ArtistService(ArtistRepository repository) {
        this.repository = repository;
    }

    public List<Artist> findAll() {
        return repository.findAll();
    }

    public ArtistDTO getArtist(Integer id) {
        return repository.findById(id)
                .map(artist -> new ArtistDTO(artist.getId(), artist.getName()))
                .orElse(null);
    }
}
