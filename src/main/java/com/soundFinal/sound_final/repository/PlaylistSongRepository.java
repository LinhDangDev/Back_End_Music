package com.soundFinal.sound_final.repository;

import com.soundFinal.sound_final.entity.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, Integer> {
}
