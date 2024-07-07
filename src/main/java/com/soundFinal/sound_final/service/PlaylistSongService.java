package com.soundFinal.sound_final.service;

import com.soundFinal.sound_final.entity.PlaylistSong;
import com.soundFinal.sound_final.repository.PlaylistSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistSongService {

    @Autowired
    private PlaylistSongRepository playlistSongRepository;

    public List<PlaylistSong> getAllPlaylistSongs(){ return playlistSongRepository.findAll(); }

    public PlaylistSong getPlaylistSongById(Integer id){ return playlistSongRepository.findById(id).orElse(null); }

    public void addOrUpdatePlaylistSong(PlaylistSong playlistSong){ playlistSongRepository.save(playlistSong); }

    public void deletePlaylistSongById(Integer id){ playlistSongRepository.deleteById(id); }
}
