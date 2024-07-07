package com.soundFinal.sound_final.controller;

import com.soundFinal.sound_final.dto.reponse.ApiResponse;
import com.soundFinal.sound_final.entity.Comment;
import com.soundFinal.sound_final.service.CommentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ApiResponse<List<Comment>> getAll(){
        return ApiResponse.<List<Comment>>builder()
                .result(commentService.getAllComments())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ApiResponse<Comment> getComment(@PathVariable Integer id){
        return ApiResponse.<Comment>builder()
                .result(commentService.getCommentById(id))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<String> addComment(@Valid @RequestBody Comment comment, BindingResult result){
        if(result.hasErrors())
            return ApiResponse.<String>builder()
                    .result("Comment add failed: " + result)
                    .build();
        commentService.addOrUpdateComment(comment);
        return ApiResponse.<String>builder()
                .result("Comment has been added")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<String> updateComment(@Valid @RequestBody Comment comment, @PathVariable Integer id, BindingResult result){
        Comment findComment = commentService.getCommentById(comment.getCommentId());
        if(result.hasErrors() || findComment == null)
            return ApiResponse.<String>builder()
                    .result("Comment update failed: " + result)
                    .build();
        commentService.addOrUpdateComment(comment);
        return ApiResponse.<String>builder()
                .result("Comment has been updated")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteComment(@PathVariable Integer id){
        commentService.deleteCommentById(id);
        return ApiResponse.<String>builder()
                .result("Comment has been deleted")
                .build();
    }
}
