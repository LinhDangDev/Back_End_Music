package com.soundFinal.sound_final.repository;

import com.soundFinal.sound_final.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;



public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
