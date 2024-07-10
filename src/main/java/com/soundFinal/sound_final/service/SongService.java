package com.soundFinal.sound_final.service;

import com.soundFinal.sound_final.entity.Song;
import com.soundFinal.sound_final.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public List<Song> getAllSongs(){ return songRepository.findAll(); }

    public Song getSongById(Integer id){ return songRepository.findById(id).orElse(null); }

    public void addOrUpdateSong(Song song){ songRepository.save(song); }

    public void deleteSongById(Integer id){ songRepository.deleteById(id); }
    public void addLike(Integer id){
        Song song = this.getSongById(id);
        if(song != null)
            song.setLikes(song.getLikes() + 1);
        this.addOrUpdateSong(song);
    }
    public void subLike(Integer id){
        Song song = this.getSongById(id);
        if(song != null)
            song.setLikes(song.getLikes() - 1);
        this.addOrUpdateSong(song);
    }
}
