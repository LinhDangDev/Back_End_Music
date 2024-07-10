package com.soundFinal.sound_final.controller;

import com.soundFinal.sound_final.dto.GenreDto;
import com.soundFinal.sound_final.dto.SongDto;
import com.soundFinal.sound_final.dto.reponse.ApiResponse;
import com.soundFinal.sound_final.entity.Genre;
import com.soundFinal.sound_final.entity.Song;
import com.soundFinal.sound_final.service.GenreService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping
    public ApiResponse<List<Genre>> getAll(){
        return ApiResponse.<List<Genre>>builder()
                .result(genreService.getAllGenres())
                .build();
    }
    @GetMapping("/{genreId}/songs")
    public ApiResponse<List<GenreDto>> getSongsByGenreId(@PathVariable Integer genreId) {
        Genre genre = genreService.getGenreById(genreId);

        if (genre == null) {
            return ApiResponse.<List<GenreDto>>builder()
                    .code(404)
                    .message("Genre not found")
                    .build();
        }

        List<GenreDto> genreDto = genre.getSongs().stream()
                .map(song -> new GenreDto(
                        song.getSongId(),
                        song.getSongTitle(),
                        "http://localhost:8080/img/" + song.getCoverImage(),
                        "http://localhost:8080/music/" + song.getFilePath()
                ))
                .collect(Collectors.toList());

        return ApiResponse.<List<GenreDto>>builder()
                .result(genreDto)
                .build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ApiResponse<Genre> getGenre(@PathVariable Integer id){
        return ApiResponse.<Genre>builder()
                .result(genreService.getGenreById(id))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<String> addGenre(@Valid @RequestBody Genre genre, BindingResult result){
        if(result.hasErrors())
            return ApiResponse.<String>builder()
                    .result("Genre add failed: " + result)
                    .build();
        genreService.addOrUpdateGenre(genre);
        return ApiResponse.<String>builder()
                .result("Genre has been added")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<String> updateGenre(@Valid @RequestBody Genre genre, @PathVariable Integer id, BindingResult result){
        Genre findGenre = genreService.getGenreById(id);
        if(result.hasErrors() || findGenre == null){
            return ApiResponse.<String>builder()
                    .result("Genre update failed: " + result)
                    .build();
        }
        genreService.addOrUpdateGenre(genre);
        return ApiResponse.<String>builder()
                .result("Genre has been updated")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteGenre(@PathVariable Integer id){
        genreService.deleteGenreById(id);
        return ApiResponse.<String>builder()
                .result("Genre has been deleted")
                .build();
    }
}
