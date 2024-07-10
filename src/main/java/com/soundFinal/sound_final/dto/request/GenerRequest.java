package com.soundFinal.sound_final.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenerRequest {
//    @Size(max = 255, message = "Genre name must not be over 255 characters")
//    @NotNull(message = "Genre name can not be null")
//    @Column(name = "genre_name")
    private String genreName;

//    @Size(max = 255, message = "Genre description must not be over 255 characters")
//    @Column(name = "description")
    private String description;
}
