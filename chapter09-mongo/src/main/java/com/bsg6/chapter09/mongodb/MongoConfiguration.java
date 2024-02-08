package com.bsg6.chapter09.mongodb;

import com.bsg6.chapter09.common.WildcardConverter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootConfiguration
@EnableMongoRepositories
@EntityScan
public class MongoConfiguration {
    @Bean
    WildcardConverter converter() {
        return new WildcardConverter("");
    }

    @Bean
    MusicService musicService(
        ArtistRepository artistRepository,
        SongRepository songRepository,
        WildcardConverter converter
    ) {
        return new MusicService(artistRepository, songRepository, converter);
    }
}
