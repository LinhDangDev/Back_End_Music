package com.soundFinal.sound_final.mapper;

import com.soundFinal.sound_final.dto.reponse.GenreResponse;
import com.soundFinal.sound_final.dto.request.GenerRequest;
import com.soundFinal.sound_final.entity.Genre;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class GenerMapperImpl implements GenerMapper {

    @Override
    public Genre toGenre(GenerRequest request) {
        if ( request == null ) {
            return null;
        }

        Genre genre = new Genre();

        genre.setGenreName( request.getGenreName() );
        genre.setDescription( request.getDescription() );

        return genre;
    }

    @Override
    public GenreResponse toGenreResponse(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreResponse.GenreResponseBuilder genreResponse = GenreResponse.builder();

        genreResponse.genreName( genre.getGenreName() );
        genreResponse.description( genre.getDescription() );

        return genreResponse.build();
    }
}
