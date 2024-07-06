package com.soundFinal.sound_final.repository;

import com.soundFinal.sound_final.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
