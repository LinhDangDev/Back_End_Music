package com.soundFinal.sound_final.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    String id;

    @NotNull(message = "User username can not be null")
    @Size(max = 255, message = "User username must not be over 255 characters")
    @Column(unique = true, name = "username",nullable = false)
    String username;

    @NotNull(message = "User password can not be null")
    @Size(max = 255, message = "User password must not be over 255 characters")
    @Column(name = "password")
    String password;

    @NotNull(message = "User email can not be null")
    @Email(message = "User email is invalid")
    @Size(max = 255, message = "User email must not be over 255 characters")
    @Column(unique = true, name = "email")
    String email;

    @Size(max = 255, message = "User avatar must not be over 255 characters")
    @Column(name = "avatar")
    String avatar;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;

    @Column(name = "count_sign_in")
    int countSignIn;

    @Column(name = "active")
    boolean active = false;

    @Column(name = "subscription")
    LocalDateTime subscription;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Playlist> playlists;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "User_Roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_name")
    )
    Set<Role> roles;
}