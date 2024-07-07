package com.soundFinal.sound_final.controller;

import com.soundFinal.sound_final.dto.reponse.ApiResponse;
import com.soundFinal.sound_final.entity.Playlist;
import com.soundFinal.sound_final.entity.PlaylistSong;
import com.soundFinal.sound_final.service.PlaylistService;
import com.soundFinal.sound_final.service.PlaylistSongService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlistsongs")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PlaylistSongController {
    @Autowired
    private PlaylistSongService playlistSongService;

    @GetMapping
    public ApiResponse<List<PlaylistSong>> getAll(){
        return ApiResponse.<List<PlaylistSong>>builder()
                .result(playlistSongService.getAllPlaylistSongs())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ApiResponse<PlaylistSong> getPlaylistSong(@PathVariable Integer id){
        return ApiResponse.<PlaylistSong>builder()
                .result(playlistSongService.getPlaylistSongById(id))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<String> addPlaylistSong(@Valid @RequestBody PlaylistSong playlistSong, BindingResult result){
        if(result.hasErrors())
            return ApiResponse.<String>builder()
                    .result("PlaylistSong add failed: " + result)
                    .build();
        playlistSongService.addOrUpdatePlaylistSong(playlistSong);
        return ApiResponse.<String>builder()
                .result("PlaylistSong has been added")
                .build();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<String> editPlaylistSong(@Valid @RequestBody PlaylistSong playlistSong, @PathVariable Integer id, BindingResult result){
        PlaylistSong findPlaylistSong = playlistSongService.getPlaylistSongById(id);
        if(result.hasErrors() || findPlaylistSong == null)
            return ApiResponse.<String>builder()
                    .result("PlaylistSong update failed: " + result)
                    .build();
        playlistSongService.addOrUpdatePlaylistSong(playlistSong);
        return ApiResponse.<String>builder()
                .result("PlaylistSong has been updated")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deletePlaylistSong(@PathVariable Integer id){
        playlistSongService.deletePlaylistSongById(id);
        return ApiResponse.<String>builder()
                .result("PlaylistSong has been deleted")
                .build();
    }
}
