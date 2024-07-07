package com.soundFinal.sound_final.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Playlist_Song")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlaylistSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "playlist_id", nullable = false)
    @JsonBackReference
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    @JsonBackReference
    private Song song;
}