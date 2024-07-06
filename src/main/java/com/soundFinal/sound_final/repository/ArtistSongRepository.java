package com.soundFinal.sound_final.repository;

import com.soundFinal.sound_final.entity.ArtistSong;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArtistSongRepository extends JpaRepository<ArtistSong, Integer> {
}
