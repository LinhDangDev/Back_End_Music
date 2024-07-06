package com.soundFinal.sound_final.repository;

import com.soundFinal.sound_final.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
}

