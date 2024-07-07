package com.soundFinal.sound_final.controller;

import com.soundFinal.sound_final.dto.reponse.ApiResponse;
import com.soundFinal.sound_final.entity.Playlist;
import com.soundFinal.sound_final.service.PlaylistService;
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
@RequestMapping("/playlists")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;
    @GetMapping
    public ApiResponse<List<Playlist>> getAll(){
        return ApiResponse.<List<Playlist>>builder()
                .result(playlistService.getAllPlaylists())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ApiResponse<Playlist> getPlaylist(@PathVariable Integer id){
        return ApiResponse.<Playlist>builder()
                .result(playlistService.getPlaylistById(id))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<String> addPlaylist(@Valid @RequestBody Playlist playlist, BindingResult result){
        if(result.hasErrors())
            return ApiResponse.<String>builder()
                    .result("Playlist add failed: " + result)
                    .build();
        playlistService.addOrUpdatePlaylist(playlist);
        return ApiResponse.<String>builder()
                .result("Playlist has been added")
                .build();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<String> editPlaylist(@Valid @RequestBody Playlist playlist, @PathVariable Integer id, BindingResult result){
        Playlist findPlaylist = playlistService.getPlaylistById(id);
        if(result.hasErrors() || findPlaylist == null)
            return ApiResponse.<String>builder()
                    .result("Playlist update failed: " + result)
                    .build();
        playlistService.addOrUpdatePlaylist(playlist);
        return ApiResponse.<String>builder()
                .result("Playlist has been updated")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deletePlaylist(@PathVariable Integer id){
        playlistService.deletePlaylistById(id);
        return ApiResponse.<String>builder()
                .result("Playlist has been deleted")
                .build();
    }
}
