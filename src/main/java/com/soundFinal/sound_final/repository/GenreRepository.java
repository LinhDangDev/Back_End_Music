package com.soundFinal.sound_final.repository;

import com.soundFinal.sound_final.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
