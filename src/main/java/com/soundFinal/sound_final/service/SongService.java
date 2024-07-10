package com.soundFinal.sound_final.service;

import com.soundFinal.sound_final.entity.Genre;
import com.soundFinal.sound_final.entity.Song;
import com.soundFinal.sound_final.repository.GenreRepository;
import com.soundFinal.sound_final.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private static final String UPLOAD_FOLDER = "Uploads/";


    @Autowired

    private SongRepository songRepository;
    @Autowired
    private GenreRepository genreRepository;


    @Transactional
    public String saveAudioFile(MultipartFile file, Integer genreId) {
        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".mp3")) {
            return "Only .mp3 audio files are allowed!";
        }

        try {
            // Ensure the upload directory exists
            Path uploadPath = Paths.get(UPLOAD_FOLDER);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Create a new Song entity
            Song song = new Song();
            song.setSongTitle(file.getOriginalFilename().replaceFirst("[.][^.]+$", ""));
            double size = round(((double) file.getSize() / 1024f) / 1024f); // In MB
            song.setSize(size);
            song.setDate(LocalDate.now());
            song.setCoverImage(null); // Setting cover image to null as it's not provided
            song.setLikes(0);
            song.setDownloads(0);
            song.setPlaylistSongs(new ArrayList<>());
            song.setArtistSongs(new ArrayList<>());
            song.setComments(new ArrayList<>());

            Optional<Genre> genreOptional = genreRepository.findById(String.valueOf(genreId));
            if (genreOptional.isPresent()) {
                song.setGenre(genreOptional.get());
            }

            // Save the Song entity to get the generated ID
            song = songRepository.save(song);
            // Construct the file path using the generated ID
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            String path = UPLOAD_FOLDER + song.getId() + "." + extension;  // Generate file path
            song.setFilePath(path);

            // Update the Song entity with the file path
            songRepository.save(song);

            // Save the file to the specified path
            byte[] bytes = file.getBytes();
            Path location = Paths.get(path);
            Files.write(location, bytes);

            return "You successfully uploaded: " + file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file: " + file.getOriginalFilename();
        }
    }

    @Transactional(readOnly = true)
    public List<Song> getAllAudioFiles() {
        return songRepository.findAll();
    }

    @Transactional
    public void deleteAudioFile(Long id) {
        Optional<Song> songOptional = songRepository.findById(id);
        if (songOptional.isPresent()) {
            // Delete the file from the file system
            Path filePath = Paths.get(songOptional.get().getFilePath());
            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Delete the song from the repository
            songRepository.deleteById(id);
        }
    }

    @Transactional(readOnly = true)
    public Song findAudioById(Long id) {
        Optional<Song> audioOptional = songRepository.findById(id);
        return audioOptional.orElse(null);
    }

    private static double round(double value) {
        return Math.round(value * 10.0) / 10.0;
    }
}
