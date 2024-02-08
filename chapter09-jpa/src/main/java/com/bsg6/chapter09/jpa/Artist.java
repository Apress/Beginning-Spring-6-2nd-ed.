package com.bsg6.chapter09.jpa;

import com.bsg6.chapter09.common.BaseArtist;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Artist implements BaseArtist<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @NonNull
    String name;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "artist",
            fetch = FetchType.EAGER
    )
    @OrderBy("votes DESC")
    @JsonIgnore
    List<Song> songs = new ArrayList<>();

    public Artist() {
    }

    public Artist(@NonNull String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Artist.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("songs=" + songs)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist artist)) return false;
        return Objects.equals(
                getId(),
                artist.getId()
        ) && Objects.equals(
                getName(),
                artist.getName()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
