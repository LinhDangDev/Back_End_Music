package com.soundFinal.sound_final.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "song")
@Data
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Long id;

    private String songTitle;

    private String filePath;

    private String coverImage;

    private int likes;

    private int downloads;

    private double size;

    private LocalDate date;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<PlaylistSong> playlistSongs;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<ArtistSong> artistSongs;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
}
