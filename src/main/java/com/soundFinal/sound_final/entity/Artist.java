package com.soundFinal.sound_final.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "Artist")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private int artistId;

    @NotNull(message = "Artist name can not be null")
    @Size(max = 255, message = "Artist name must not be over 255 characters")
    @Column(name = "artist_name")
    private String artistName;

    @Size(max = 255, message = "Artist avatar must not be over 255 characters")
    @Column(name = "avatar")
    private String avatar;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Album> albums;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonManagedReference
//    @JsonIgnore
    private List<ArtistSong> artistSongs;
}