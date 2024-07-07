package com.soundFinal.sound_final.controller;

import com.soundFinal.sound_final.dto.reponse.ApiResponse;
import com.soundFinal.sound_final.entity.Album;
import com.soundFinal.sound_final.service.AlbumService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ApiResponse<List<Album>> getAll(){
        return ApiResponse.<List<Album>>builder()
                .result(albumService.getAllAlbums())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ApiResponse<Album> getAlbum(@PathVariable Integer id){
        return ApiResponse.<Album>builder()
                .result(albumService.getAlbumById(id))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<String> addAlbum(@Valid @RequestBody Album album, BindingResult result){
        if(result.hasErrors())
            return ApiResponse.<String>builder()
                    .result("Album add failed: " + result)
                    .build();
        albumService.addOrUpdateAlbum(album);
        return ApiResponse.<String>builder()
               .result("Album has been added")
               .build();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<String> editAlbum(@Valid @RequestBody Album album, @PathVariable Integer id, BindingResult result){
        Album findAlbum = albumService.getAlbumById(id);
        if(result.hasErrors() || findAlbum == null)
            return ApiResponse.<String>builder()
                    .result("Album update failed: " + result)
                    .build();
        albumService.addOrUpdateAlbum(album);
        return ApiResponse.<String>builder()
                .result("Album has been updated")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteAlbum(@PathVariable Integer id){
        albumService.deleteAlbumById(id);
        return ApiResponse.<String>builder()
                .result("Album has been deleted")
                .build();
    }
}
