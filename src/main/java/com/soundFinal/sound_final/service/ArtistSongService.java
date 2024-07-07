package com.soundFinal.sound_final.service;

import com.soundFinal.sound_final.entity.ArtistSong;
import com.soundFinal.sound_final.repository.ArtistSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistSongService {

    @Autowired
    private ArtistSongRepository artistSongRepository;

    public List<ArtistSong> getAllArtistSongs(){ return artistSongRepository.findAll(); }

    public ArtistSong getArtistSongById(Integer id){ return artistSongRepository.findById(id).orElse(null); }

    public void addOrUpdateArtistSong(ArtistSong artistSong){ artistSongRepository.save(artistSong); }

    public void deleteArtistSongById(Integer id){ artistSongRepository.deleteById(id); }
}
