package com.soundFinal.sound_final.service;

import com.soundFinal.sound_final.entity.Artist;
import com.soundFinal.sound_final.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private  ArtistRepository artistRepository;

    public List<Artist> getAllArtists(){ return artistRepository.findAll(); }

    public Artist getArtistById(Integer id){ return artistRepository.findById(id).orElse(null); }

    public void addOrUpdateArtist(Artist artist){ artistRepository.save(artist); }

    public void deleteArtistById(Integer id){ artistRepository.deleteById(id); }
}
