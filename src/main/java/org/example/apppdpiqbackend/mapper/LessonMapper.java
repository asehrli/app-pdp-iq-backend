package org.example.apppdpiqbackend.mapper;

import org.example.apppdpiqbackend.entity.Lesson;
import org.example.apppdpiqbackend.payload.AddLessonDTO;
import org.example.apppdpiqbackend.payload.LessonDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    LessonDTO toDto(Lesson lesson);
    List<LessonDTO> toDtoList(List<Lesson> module);

    Lesson toLesson(AddLessonDTO addLessonDTO);

}
