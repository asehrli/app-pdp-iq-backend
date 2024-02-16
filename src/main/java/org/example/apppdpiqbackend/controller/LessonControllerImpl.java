package org.example.apppdpiqbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.EditLessonDTO;
import org.example.apppdpiqbackend.payload.LessonDTO;
import org.example.apppdpiqbackend.service.LessonService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class LessonControllerImpl implements LessonController {
    private final LessonService lessonService;

    @Override
    public HttpEntity<ApiResponse<List<LessonDTO>>> all() {
        return ResponseEntity.ok(lessonService.list());
    }

    @Override
    public HttpEntity<ApiResponse<LessonDTO>> add(AddLessonDTO addLessonDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.add(addLessonDTO));
    }

    @Override
    public HttpEntity<ApiResponse<LessonDTO>> one(UUID id) {
        return ResponseEntity.ok(lessonService.one(id));
    }

    @Override
    public HttpEntity<ApiResponse<LessonDTO>> edit(UUID id, EditLessonDTO editLessonDTO) {
        return ResponseEntity.accepted().body(lessonService.edit(id, editLessonDTO));
    }

    @Override
    public HttpEntity<ApiResponse<?>> delete(UUID id) {
        lessonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
