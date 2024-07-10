package com.soundFinal.sound_final.controller;

import com.soundFinal.sound_final.dto.reponse.GenreResponse;
import com.soundFinal.sound_final.dto.reponse.PermissionResponse;
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
public class  GenreController {

    GenreService genreService;

    @PostMapping
    ApiResponse<GenreResponse> create(@RequestBody GenerRequest request){
        return ApiResponse.<GenreResponse>builder()
                .result(genreService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<GenreResponse>> getAll(){
        return ApiResponse.<List<GenreResponse>>builder()
                .result(genreService.getAll())
                .build();
    }

    @DeleteMapping("/{genre}")
    ApiResponse<Void> delete(@PathVariable String genre){
        genreService.delete(genre);
        return ApiResponse.<Void>builder().build();
    }
}
