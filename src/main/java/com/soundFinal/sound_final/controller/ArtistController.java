package com.soundFinal.sound_final.controller;

import com.soundFinal.sound_final.dto.reponse.ApiResponse;
import com.soundFinal.sound_final.entity.Artist;
import com.soundFinal.sound_final.service.ArtistService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.soundFinal.sound_final.dto.ArtistDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artists")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping
    public ApiResponse<List<Artist>> getAll() {
        return ApiResponse.<List<Artist>>builder()
                .result(artistService.getAllArtists())
                .build();
    }
    @GetMapping("/custom-info")
    public ApiResponse<List<ArtistDto>> getCustomInformation() {
        List<ArtistDto> artistDtos = artistService.getAllArtists().stream()
                .map(artist -> new ArtistDto(
                        artist.getArtistId(),
                        artist.getArtistName(),
                        "http://localhost:8080/img/" + artist.getAvatar()
                ))
                .collect(Collectors.toList());

        return ApiResponse.<List<ArtistDto>>builder()
                .result(artistDtos)
                .build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ApiResponse<Artist> getArtist(@PathVariable Integer id) {
        return ApiResponse.<Artist>builder()
                .result(artistService.getArtistById(id))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<String> addArtist(@Valid @RequestBody Artist artist, BindingResult result) {
        if (result.hasErrors())
            return ApiResponse.<String>builder()
                    .result("Artist add failed: " + result)
                    .build();
        artistService.addOrUpdateArtist(artist);
        return ApiResponse.<String>builder()
                .result("Artist has been added!")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<String> updateArtist(@Valid @RequestBody Artist artist, @PathVariable Integer id, BindingResult result) {
        Artist findArtist = artistService.getArtistById(id);
        if (result.hasErrors() || findArtist == null)
            return ApiResponse.<String>builder()
                    .result("Artist update failed: " + result)
                    .build();
        artistService.addOrUpdateArtist(artist);
        return ApiResponse.<String>builder()
                .result("Artist has been updated")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteArtist(@PathVariable Integer id){
        artistService.deleteArtistById(id);
        return ApiResponse.<String>builder()
                .result("Artist has been deleted")
                .build();
    }
}
