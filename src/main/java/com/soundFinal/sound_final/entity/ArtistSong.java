package com.soundFinal.sound_final.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Artist_Song")
@Data
public class ArtistSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_song_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;
}