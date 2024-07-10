package com.soundFinal.sound_final.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Genre")
@Data
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private int genreId;

    @Size(max = 255, message = "Genre name must not be over 255 characters")
    @NotNull(message = "Genre name can not be null")
    @Column(name = "genre_name")
    private String genreName;

    @Size(max = 255, message = "Genre description must not be over 255 characters")
    @Column(name = "description")
    private String description;
//
    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
    private List<Song> songs;
}