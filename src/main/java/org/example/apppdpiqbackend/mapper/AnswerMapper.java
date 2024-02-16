package org.example.apppdpiqbackend.mapper;

import org.example.apppdpiqbackend.entity.Answer;
import org.example.apppdpiqbackend.entity.Question;
import org.example.apppdpiqbackend.exception.MyNotFoundException;
import org.example.apppdpiqbackend.payload.AnswerDto;
import org.example.apppdpiqbackend.repository.QuestionRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AnswerMapper {
    /*
     INSTANCE mapperning bitta nusxasi borligini taminlaydi, Singleton Pattern, Thread-Safety, Performance taminlab beradi
     Mappers.getMapper usuli tarmoqli xavfsiz singleton nusxasini qaytaradi,
     shuning uchun ko'p tarmoqli muhitda mapper vositasidan foydalanganda bir vaqtning o'zida kirish muammolari haqida tashvishlanishingiz shart emas.
    */
    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    @Mappings({
            @Mapping(target = "questionId", source = "question.id")
    })
    AnswerDto toDto(Answer answer);

    @Mappings({
            @Mapping(target = "question", expression = "java(getQuestion(answerDto.getQuestionId(),questionRepository))")
    })
    Answer toAnswer(AnswerDto answerDto, QuestionRepository questionRepository);

    default Question getQuestion(UUID questionId, QuestionRepository questionRepository) {
        return questionRepository.findById(questionId).orElseThrow(() -> new MyNotFoundException("Question topilmadi"));
    }

    List<AnswerDto> toAnswersDto(List<Answer> answers);
}
