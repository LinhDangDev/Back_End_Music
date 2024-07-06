package com.soundFinal.sound_final.repository;

import com.soundFinal.sound_final.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
//    boolean existsByUsername(String username); // kiem tra ton tai
    boolean existsByEmailIgnoreCase(String email); // kiem tra email co ton tai hay khong
    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findByUsername(String username);

}
