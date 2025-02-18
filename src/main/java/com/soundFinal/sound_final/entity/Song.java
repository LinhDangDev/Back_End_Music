package com.soundFinal.sound_final.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "song")
@Data
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private int songId;

    @NotNull(message = "Song title can not be null")
    @Size(max = 255, message = "Song title must not be over 255 characters")
    @Column(name = "song_tittle")
    private String songTitle;

    @Column(name = "release_date")
    private Integer releaseDate;

    @Column(name = "duration")
    private LocalTime duration;

    @NotNull(message = "Song file path can not be null")
    @Size(max = 255, message = "Song file path must not be over 255 characters")
    @Column(name = "file_path")
    private String filePath;

    @Size(max = 255, message = "Song image must not be over 255 characters")
    @Column(name = "image")
    private String coverImage;

    @Column(name = "likes")
    private int likes = 0;

    @Column(name = "downloads")
    private int downloads = 0;

    private double size;

    private LocalDate date;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PlaylistSong> playlistSongs;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    @JsonManagedReference
//    @JsonIgnore
    private List<ArtistSong> artistSongs;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comments;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "genre_id")
    private Genre genre;
}