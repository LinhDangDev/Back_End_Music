package com.soundFinal.sound_final.service;

import com.soundFinal.sound_final.entity.Playlist;
import com.soundFinal.sound_final.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    public List<Playlist> getAllPlaylists(){ return playlistRepository.findAll(); }

    public Playlist getPlaylistById(Integer id){ return playlistRepository.findById(id).orElse(null); }

    public void addOrUpdatePlaylist(Playlist playlist){ playlistRepository.save(playlist); }

    public void deletePlaylistById(Integer id){ playlistRepository.deleteById(id); }
}
