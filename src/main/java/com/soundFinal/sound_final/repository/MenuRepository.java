package com.soundFinal.sound_final.repository;

import com.soundFinal.sound_final.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
