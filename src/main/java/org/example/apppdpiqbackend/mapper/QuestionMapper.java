package org.example.apppdpiqbackend.mapper;

import org.example.apppdpiqbackend.entity.Lesson;
import org.example.apppdpiqbackend.entity.Question;
import org.example.apppdpiqbackend.exception.MyNotFoundException;
import org.example.apppdpiqbackend.payload.QuestionDto;
import org.example.apppdpiqbackend.repository.LessonRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface QuestionMapper {

    /*
     INSTANCE mapperning bitta nusxasi borligini taminlaydi, Singleton Pattern, Thread-Safety, Performance taminlab beradi
     Mappers.getMapper usuli tarmoqli xavfsiz singleton nusxasini qaytaradi,
     shuning uchun ko'p tarmoqli muhitda mapper vositasidan foydalanganda bir vaqtning o'zida kirish muammolari haqida tashvishlanishingiz shart emas.
    */
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @Mappings({
            @Mapping(target = "lessonId", source = "lesson.id")
    })
    QuestionDto toDto(Question question);


    @Mappings({
            @Mapping(target = "lesson", expression = "java(getLesson(questionDto.getLessonId(),lessonRepository))")
    })
    Question toQuestion(QuestionDto questionDto, LessonRepository lessonRepository);

    List<QuestionDto> toQuestionList(List<Question> questionList);

    default Lesson getLesson(UUID lessonId, LessonRepository lessonRepository) {
        return lessonRepository.findById(lessonId).orElseThrow(() -> new MyNotFoundException("Lesson topilmadi"));
    }

}
