package com.soundFinal.sound_final.repository;

import com.soundFinal.sound_final.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArtistRepository extends JpaRepository<Artist, Integer> {
}
