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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreService {

    GenreRepository genreRepository;
    GenerMapper genreMapper;

    @Transactional
    public GenreResponse createGenre(GenerRequest request) {
        Genre genre = genreMapper.toGenre(request);
        genre = genreRepository.save(genre);
        return genreMapper.toGenreResponse(genre);
    }

    @Transactional(readOnly = true)
    public List<GenreResponse> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream().map(genreMapper::toGenreResponse).toList();
    }

    @Transactional(readOnly = true)
    public GenreResponse getGenreById(Long id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        return genreOptional.map(genreMapper::toGenreResponse).orElse(null);
    }

    @Transactional
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}
