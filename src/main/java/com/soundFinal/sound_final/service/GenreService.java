package com.soundFinal.sound_final.service;

import com.soundFinal.sound_final.dto.request.GenerRequest;
import com.soundFinal.sound_final.dto.reponse.GenreResponse;
import com.soundFinal.sound_final.entity.Genre;
import com.soundFinal.sound_final.mapper.GenerMapper;
import com.soundFinal.sound_final.repository.GenreRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreService {


    GenreRepository genreRepository;
    GenerMapper generMapper;


    public GenreResponse create (GenerRequest request) {
        Genre genre = generMapper.toGenre(request);
        genre = genreRepository.save(genre);
        return generMapper.toGenreResponse(genre);

    }

    public List<GenreResponse> getAll(){
        var genres = genreRepository.findAll();
        return genres.stream().map(generMapper::toGenreResponse).toList();
    }

    public void delete(String genre){
        genreRepository.deleteById(genre);
    }

}
