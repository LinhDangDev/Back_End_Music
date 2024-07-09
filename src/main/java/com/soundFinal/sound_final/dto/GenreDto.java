package com.soundFinal.sound_final.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {
    private int id;
    private String name;
    private String imageUrl;
    private String musicUrl;

}