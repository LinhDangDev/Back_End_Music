package com.soundFinal.sound_final.service;

import com.soundFinal.sound_final.entity.Genre;
import com.soundFinal.sound_final.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.soundFinal.sound_final.dto.request.GenerRequest;
import com.soundFinal.sound_final.dto.reponse.GenreResponse;
import com.soundFinal.sound_final.mapper.GenerMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;
    GenerMapper genreMapper;

    public List<Genre> getAllGenres(){ return genreRepository.findAll(); }

    public Genre getGenreById(Integer id){ return genreRepository.findById(id).orElse(null); }

    public void addOrUpdateGenre(Genre genre){ genreRepository.save(genre); }

    public void deleteGenreById(Integer id){ genreRepository.deleteById(id);}

    @Transactional
    public GenreResponse _createGenre(GenerRequest request) {
        Genre genre = genreMapper.toGenre(request);
        genre = genreRepository.save(genre);
        return genreMapper.toGenreResponse(genre);
    }

    @Transactional(readOnly = true)
    public List<GenreResponse> _getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream().map(genreMapper::toGenreResponse).toList();
    }

    @Transactional(readOnly = true)
    public GenreResponse _getGenreById(Integer id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        return genreOptional.map(genreMapper::toGenreResponse).orElse(null);
    }

    @Transactional
    public void _deleteGenre(Integer id) {
        genreRepository.deleteById(id);
    }


}
