package com.soundFinal.sound_final.controller;

import com.soundFinal.sound_final.dto.reponse.ApiResponse;
import com.soundFinal.sound_final.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/songs")
public class AudioController {

    private static final String UPLOAD_FOLDER = "Uploads/";

    @Autowired
    private SongService audioService;

    @PostMapping("/upload")
    public ApiResponse<String> uploadAudioFile(@RequestParam("file") MultipartFile file,Integer genreId) {
        String result = audioService.saveAudioFile(file, genreId);
        return ApiResponse.<String>builder()
                .result(result)
                .build();
    }

}