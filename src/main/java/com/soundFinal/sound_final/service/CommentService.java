package com.soundFinal.sound_final.service;

import com.soundFinal.sound_final.entity.Comment;
import com.soundFinal.sound_final.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments(){ return commentRepository.findAll(); }

    public Comment getCommentById(Integer id){ return commentRepository.findById(id).orElse(null); }

    public void addOrUpdateComment(Comment comment){ commentRepository.save(comment); }

    public void deleteCommentById(Integer id){ commentRepository.deleteById(id); }
}
