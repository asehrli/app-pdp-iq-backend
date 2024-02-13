package org.example.apppdpiqbackend.service;

import org.example.apppdpiqbackend.payload.AddLessonDTO;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.EditLessonDTO;
import org.example.apppdpiqbackend.payload.LessonDTO;

import java.util.List;
import java.util.UUID;

public interface LessonService {
    ApiResponse<List<LessonDTO>> list();

    ApiResponse<LessonDTO> add(AddLessonDTO addLessonDTO);

    ApiResponse<LessonDTO> one(UUID id);

    ApiResponse<LessonDTO> edit(UUID id, EditLessonDTO editLessonDTO);

    void delete(UUID id);
}
