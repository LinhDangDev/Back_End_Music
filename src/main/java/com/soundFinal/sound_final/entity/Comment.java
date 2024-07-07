package com.soundFinal.sound_final.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Comment")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;

    @Column(name = "content")
    @NotNull(message = "Comment content can not be null")
    @Size(max = 255, message = "Comment content must not be over 255 characters")
    private String content;

    @Column(name = "create_date")
    @NotNull(message = "Comment created date can not be null")
    private LocalDate createDate;

    @Column(name = "parentId")
    private int parentId;

    @Column(name = "rate")
    private int rate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    @JsonBackReference
    private Song song;
}