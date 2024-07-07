package com.soundFinal.sound_final.service;

import com.soundFinal.sound_final.entity.Album;
import com.soundFinal.sound_final.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public List<Album> getAllAlbums(){ return albumRepository.findAll(); }

    public Album getAlbumById(Integer id){ return albumRepository.findById(id).orElse(null); }

    public void addOrUpdateAlbum(Album album){ albumRepository.save(album); }

    public void deleteAlbumById(Integer id){ albumRepository.deleteById(id); }
}
