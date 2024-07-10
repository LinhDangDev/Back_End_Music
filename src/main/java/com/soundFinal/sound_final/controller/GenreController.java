package com.soundFinal.sound_final.controller;

import com.soundFinal.sound_final.dto.reponse.GenreResponse;
import com.soundFinal.sound_final.dto.request.ApiResponse;
import com.soundFinal.sound_final.dto.request.GenerRequest;
import com.soundFinal.sound_final.service.GenreService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class GenreController {

    GenreService genreService;

    @PostMapping
    public ApiResponse<GenreResponse> create(@RequestBody GenerRequest request){
        return ApiResponse.<GenreResponse>builder()
                .result(genreService.createGenre(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<GenreResponse>> getAll(){
        return ApiResponse.<List<GenreResponse>>builder()
                .result(genreService.getAllGenres())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<GenreResponse> getById(@PathVariable Long id){
        GenreResponse genreResponse = genreService.getGenreById(id);
        if (genreResponse != null) {
            return ApiResponse.<GenreResponse>builder()
                    .result(genreResponse)
                    .build();
        } else {
            return ApiResponse.<GenreResponse>builder()
                    .code(404)
                    .message("Genre not found")
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id){
        genreService.deleteGenre(id);
        return ApiResponse.<Void>builder().build();
    }
}
