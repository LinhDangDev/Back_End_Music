package com.soundFinal.sound_final.repository;
import com.soundFinal.sound_final.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Integer> {
}

