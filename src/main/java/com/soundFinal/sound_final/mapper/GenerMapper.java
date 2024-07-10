package com.soundFinal.sound_final.mapper;

import com.soundFinal.sound_final.dto.request.GenerRequest;
import com.soundFinal.sound_final.dto.reponse.GenreResponse;
import com.soundFinal.sound_final.entity.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenerMapper {
    Genre toGenre(GenerRequest request);

    GenreResponse toGenreResponse(Genre genre);
}
