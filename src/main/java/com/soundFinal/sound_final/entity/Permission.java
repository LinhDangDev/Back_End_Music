package com.soundFinal.sound_final.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission {
    @Id
    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @ManyToMany(mappedBy = "permissions")
    Set<Role> roles;
}