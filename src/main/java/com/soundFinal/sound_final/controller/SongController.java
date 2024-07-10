package com.soundFinal.sound_final.controller;

import com.soundFinal.sound_final.dto.SongDto;
import com.soundFinal.sound_final.dto.reponse.ApiResponse;
import com.soundFinal.sound_final.entity.Song;
import com.soundFinal.sound_final.service.SongService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping
    public ApiResponse<List<Song>> getAll(){
        return ApiResponse.<List<Song>>builder()
                .result(songService.getAllSongs())
                .build();
    }
    @GetMapping("/{id}/play")
    public ApiResponse<SongDto> getSongForPlay(@PathVariable Integer id) {
        Song song = songService.getSongById(id);

        if (song == null) {
            return ApiResponse.<SongDto>builder()
                    .code(404)
                    .message("Song not found")
                    .build();
        }

        // Lấy thông tin ca sĩ từ ArtistSong
        String artistName = song.getArtistSongs().get(0).getArtist().getArtistName();

        SongDto songDto = new SongDto(
                song.getSongId(),
                song.getSongTitle(),
                artistName,
                "http://localhost:8080/img/" + song.getCoverImage(),
                "http://localhost:8080/music/" + song.getFilePath()
        );

        return ApiResponse.<SongDto>builder()
                .result(songDto)
                .build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ApiResponse<Song> getSong(@PathVariable Integer id){
        return ApiResponse.<Song>builder()
                .result(songService.getSongById(id))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<String> addSong(@Valid @RequestBody Song song, BindingResult result){
        if(result.hasErrors())
            return ApiResponse.<String>builder()
                    .result("Song add failed: " + result)
                    .build();
        songService.addOrUpdateSong(song);
        return ApiResponse.<String>builder()
                .result("Song has been added")
                .build();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<String> editSong(@Valid @RequestBody Song song, @PathVariable Integer id, BindingResult result){
        Song findSong = songService.getSongById(id);
        if(result.hasErrors() || findSong == null)
            return ApiResponse.<String>builder()
                    .result("Song update failed: " + result)
                    .build();
        songService.addOrUpdateSong(song);
        return ApiResponse.<String>builder()
                .result("Song has been updated")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteSong(@PathVariable Integer id){
        songService.deleteSongById(id);
        return ApiResponse.<String>builder()
                .result("Song has been deleted")
                .build();
    }
}
