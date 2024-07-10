package com.soundFinal.sound_final.repository;

import com.soundFinal.sound_final.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findById(Long id);  // Changed parameter type to Long
}