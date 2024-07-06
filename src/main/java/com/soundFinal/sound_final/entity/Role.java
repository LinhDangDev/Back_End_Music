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
public class Role {
    @Id
    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @ManyToMany
    @JoinTable(
            name = "Role_Permissions",
            joinColumns = @JoinColumn(name = "role_name"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    Set<Permission> permissions;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;
}