package com.soundFinal.sound_final.controller;

import com.soundFinal.sound_final.dto.reponse.ApiResponse;
import com.soundFinal.sound_final.entity.ArtistSong;
import com.soundFinal.sound_final.service.ArtistSongService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@RestController
@RequestMapping("/artistSong")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ArtistSongController {
    @Autowired
    private ArtistSongService artistSongService;

    @GetMapping
    public ApiResponse<List<ArtistSong>> getAll(){
        return ApiResponse.<List<ArtistSong>>builder()
                .result(artistSongService.getAllArtistSongs())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ApiResponse<ArtistSong> getArtistSong(@PathVariable Integer id){
        return ApiResponse.<ArtistSong>builder()
                .result(artistSongService.getArtistSongById(id))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<String> addArtistSong(@Valid @RequestBody ArtistSong artistSong, BindingResult result){
        if(result.hasErrors())
            return ApiResponse.<String>builder()
                    .result("ArtistSong add failed: " + result)
                    .build();
        artistSongService.addOrUpdateArtistSong(artistSong);
        return ApiResponse.<String>builder()
                .result("ArtistSong has been added")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<String> updateArtistSong(@Valid @RequestBody ArtistSong artistSong, @PathVariable Integer id, BindingResult result){
        ArtistSong findArtistSong = artistSongService.getArtistSongById(id);
        if(result.hasErrors() || findArtistSong == null)
            return ApiResponse.<String>builder()
                    .result("ArtistSong update failed: " + result)
                    .build();
        artistSongService.addOrUpdateArtistSong(artistSong);
        return ApiResponse.<String>builder()
                .result("ArtistSong has been updated")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ApiResponse<String> deleteArtistSong(@PathVariable Integer id){
        artistSongService.deleteArtistSongById(id);
        return ApiResponse.<String>builder()
                .result("ArtistSong has been deleted")
                .build();
    }
}
