package com.soundFinal.sound_final.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "Menu")
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int menuId;

    @NotNull(message = "Menu link can not be null")
    @Size(max = 255, message = "Menu link must not be over 255 characters")
    @Column(name = "link")
    private String link;

    @Size(max = 255, message = "Menu title must not be over 255 characters")
    @Column(name = "title")
    private String title;

    @NotNull(message = "Menu order can not be null")
    @Column(name = "order_list")
    private int order;
}