package com.soundFinal.sound_final.repository;

import com.soundFinal.sound_final.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username); // kiem tra ton tai
    boolean existsByEmailIgnoreCase(String email); // kiem tra email co ton tai hay khong
    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findByUsername(String username);


    @Transactional
    @Modifying
    @Query("update User u set u.password = ?2 where u.email = ?1")
    void updatePassword(String email, String password);
}


