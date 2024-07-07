package com.soundFinal.sound_final.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Album")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private int albumId;

    @Size(max = 255, message = "Album name must not be over 255 characters")
    @Column(name = "album_name")
    @NotNull(message = "Album name can not be null")
    private String albumName;

    @Size(max = 255, message = "Album description must not be over 255 characters")
    @Column(name = "description")
    private String description;

    @Past(message = "Album release year must not in the future")
    @Column(name = "release_date")
    @NotNull(message = "Album release year can not be null")
    private LocalDateTime releaseDate;

    @Size(max = 255, message = "Album image must not be over 255 characters")
    @Column(name = "image")
    private String coverImage;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;
}