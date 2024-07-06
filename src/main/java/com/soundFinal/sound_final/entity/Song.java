package com.soundFinal.sound_final.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Song")
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "songId")
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

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<PlaylistSong> playlistSongs;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<ArtistSong> artistSongs;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
}