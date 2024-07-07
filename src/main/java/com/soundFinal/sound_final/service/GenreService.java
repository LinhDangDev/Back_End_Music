package com.soundFinal.sound_final.service;

import com.soundFinal.sound_final.entity.Genre;
import com.soundFinal.sound_final.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAllGenres(){ return genreRepository.findAll(); }

    public Genre getGenreById(Integer id){ return genreRepository.findById(id).orElse(null); }

    public void addOrUpdateGenre(Genre genre){ genreRepository.save(genre); }

    public void deleteGenreById(Integer id){ genreRepository.deleteById(id);}
}
