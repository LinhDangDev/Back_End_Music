package com.soundFinal.sound_final.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Playlist")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private int playlistId;

    @NotNull(message = "Playlist name can not be null")
    @Size(max = 255, message = "Playlist name must not be over 255 characters")
    @Column(name = "playlist_name")
    private String playlistName;

    @NotNull(message = "Playlist created date can not be null")
    @Column(updatable = false, name = "create_date")
    @CreationTimestamp
    private LocalDateTime createdDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PlaylistSong> playlistSongs;
}