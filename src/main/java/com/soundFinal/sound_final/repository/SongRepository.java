package com.soundFinal.sound_final.repository;

import com.soundFinal.sound_final.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
}