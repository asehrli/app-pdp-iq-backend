package org.example.apppdpiqbackend.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.apppdpiqbackend.entity.Lesson;
import org.example.apppdpiqbackend.exception.MyConflictException;
import org.example.apppdpiqbackend.exception.MyNotFoundException;
import org.example.apppdpiqbackend.mapper.LessonMapper;
import org.example.apppdpiqbackend.payload.AddLessonDTO;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.EditLessonDTO;
import org.example.apppdpiqbackend.payload.LessonDTO;
import org.example.apppdpiqbackend.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonServiceImpl implements LessonService {


    final LessonRepository lessonRepository;

    final LessonMapper lessonMapper;


    @Override
    public ApiResponse<List<LessonDTO>> list() {
        return ApiResponse.success(lessonMapper.toDtoList(lessonRepository.findAll()));
    }

    @Override
    public ApiResponse<LessonDTO> add(AddLessonDTO addLessonDTO) {
        // todo existsByNameAndModuleId
        if (lessonRepository.existsByName(addLessonDTO.name()))
            throw new MyConflictException("Lesson already exists!");

        return ApiResponse.success(lessonMapper.toDto(lessonRepository.save(lessonMapper.toLesson(addLessonDTO))));
    }

    @Override
    public ApiResponse<LessonDTO> one(UUID id) {
        return ApiResponse.success(lessonMapper.toDto(
                lessonRepository.findById(id).orElseThrow(() ->
                        new MyNotFoundException("Lesson not found by id"))));
    }

    @Override
    public ApiResponse<LessonDTO> edit(UUID id, EditLessonDTO editLessonDTO) {
        Lesson editLesson = lessonRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Lesson not found by id"));

        // todo existsByNameAndModuleIdAndIdNot
        if (lessonRepository.existsByNameAndIdNot(editLesson.getName(), id))
            throw new MyConflictException("Lesson already exists!");

        editLesson.setName(editLesson.getName());
        // todo module is not edited
        editLesson.setModule(editLesson.getModule());

        return ApiResponse.success(lessonMapper.toDto(lessonRepository.save(editLesson)));
    }

    @Override
    public void delete(UUID id) {
        if (!lessonRepository.existsById(id))
            throw new MyNotFoundException("Role not found by id!");

        lessonRepository.deleteById(id);
    }
}
