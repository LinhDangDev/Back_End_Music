package com.soundFinal.sound_final.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Playlist_Song")
@Data
public class PlaylistSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;
}