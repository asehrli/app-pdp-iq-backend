package org.example.apppdpiqbackend.controller;

import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.EditLessonDTO;
import org.example.apppdpiqbackend.payload.LessonDTO;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(LessonController.BASE_PATH)

public interface LessonController {
    String BASE_PATH = "/api/lesson";

    @GetMapping
    HttpEntity<ApiResponse<List<LessonDTO>>> all();

    @PostMapping
    HttpEntity<ApiResponse<LessonDTO>> add(@RequestBody AddLessonDTO addLessonDTO);

    @GetMapping("/{id}")
    HttpEntity<ApiResponse<LessonDTO>> one(@PathVariable UUID id);

    @PutMapping("/{id}")
    HttpEntity<ApiResponse<LessonDTO>> edit(@PathVariable UUID id,
                                            @RequestBody EditLessonDTO editLessonDTO);

    @DeleteMapping("/{id}")
    HttpEntity<ApiResponse<?>> delete(@PathVariable UUID id);


}
