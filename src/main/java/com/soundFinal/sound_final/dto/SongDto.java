package com.soundFinal.sound_final.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongDto {
    private int id;
    private String title; // Thay đổi tên biến cho phù hợp
    private String artist; // Thêm trường artist
    private String imageUrl;
    private String musicUrl;

}