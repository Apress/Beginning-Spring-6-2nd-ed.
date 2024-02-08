package com.bsg6.chapter11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobNotificationListener implements JobExecutionListener {
    private static final Logger log =
        LoggerFactory.getLogger(JobNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    public JobNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("DONE: Time to verify the results");

            log.info("Let's look at our list of artists!");
            jdbcTemplate.query("SELECT name FROM artist",
                    (rs, row) -> new Artist(
                            rs.getString(1)
                            )
            ).forEach(artist ->
                log.info("Found <{{}}> in the database.",
                    artist.getName()));

            log.info("Let's look at our list of songs!");
            jdbcTemplate.query("SELECT name FROM song",
                    (rs, row) -> new Song(
                            rs.getString(1))
            ).forEach(song ->
                log.info("Found <{{}}> in the database.",
                    song.getName()));
        }
    }

}