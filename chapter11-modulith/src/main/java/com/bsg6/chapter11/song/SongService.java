package com.bsg6.chapter11.song;

import com.bsg6.chapter11.artist.ArtistDTO;
import com.bsg6.chapter11.artist.ArtistService;
import com.bsg6.chapter11.song.internal.SongRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    private final SongRepository repository;
    private final ArtistService artistService;

    public SongService(SongRepository repository,
                       ArtistService artistService) {
        this.repository = repository;
        this.artistService = artistService;
    }

    public SongDTO getSong(Integer id) {
        return repository.findById(id)
            .map(song -> {
                ArtistDTO artistDTO =
                    artistService.getArtist(song.getArtistId());
                return artistDTO != null ?
                    new SongDTO(id, song.getTitle(), artistDTO) :
                    null;
            })
            .orElse(null);
    }
}
