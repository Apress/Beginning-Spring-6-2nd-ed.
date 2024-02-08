package com.bsg6.chapter11;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

// tag::declaration[]
@Configuration
public class GatewayConfiguration {

    @Value("${file.artists.input}")
    private String artistsFileInput;

    @Value("${file.songs.input}")
    private String songsFileInput;
    // end::declaration[]

    // tag::readers[]
    @Bean
    public FlatFileItemReader artistReader() {
        return new FlatFileItemReaderBuilder().name("artistsReader")
            .resource(new ClassPathResource(artistsFileInput))
            .delimited()
            .names(new String[] { "id", "name" })
            .fieldSetMapper(new BeanWrapperFieldSetMapper() {{
                setTargetType(Artist.class);
            }})
            .build();
    }

    @Bean
    public FlatFileItemReader songReader() {
        return new FlatFileItemReaderBuilder().name("songsReader")
            .resource(new ClassPathResource(songsFileInput))
            .delimited()
            .names(new String[] { "id", "name", "artistId" })
            .fieldSetMapper(new BeanWrapperFieldSetMapper() {{
                setTargetType(Song.class);
            }})
            .build();
    }
    // end::readers[]

    // tag::writers[]
    @Bean
    public JdbcBatchItemWriter<Artist> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Artist>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO artist (id, name) VALUES (:id, :name)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Song> songsWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Song>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO song (id, artist_id, name, votes) VALUES (:id, :artistId, :name, 0)")
                .dataSource(dataSource)
                .build();
    }
    // end::writers[]


    // tag::jobs[]
    @Bean
    public Job importJob(JobRepository jobRepository, Step step1, Step step2, JobNotificationListener listener) {
        return new JobBuilder("importJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step1)
                .next(step2)
                .build();
    }
    // end::jobs[]

    // tag::steps[]
    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager, JdbcBatchItemWriter<Artist> writer) {
        return new StepBuilder("step1", jobRepository)
                .<Artist, Artist> chunk(5, transactionManager)
                .reader(artistReader())
                .writer(writer)
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager, JdbcBatchItemWriter<Song> songsWriter) {
        return new StepBuilder("step2", jobRepository)
                .<Song, Song> chunk(10, transactionManager)
                .reader(songReader())
                .processor(processor())
                .writer(songsWriter)
                .build();
    }
    // end::steps[]

    // tag::processor[]
    @Bean
    public SongProcessor processor() {
        return new SongProcessor();
    }
    // end::processor[]
}
