package org.example.apppdpiqbackend.service.question;

import lombok.RequiredArgsConstructor;
import org.example.apppdpiqbackend.entity.Question;
import org.example.apppdpiqbackend.exception.MyNotFoundException;
import org.example.apppdpiqbackend.mapper.QuestionMapper;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.QuestionDto;
import org.example.apppdpiqbackend.repository.LessonRepository;
import org.example.apppdpiqbackend.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final LessonRepository lessonRepository;


    public ApiResponse<QuestionDto> one(UUID questionId) {
        return ApiResponse.success(questionMapper.toDto(questionRepository.findById(questionId).orElseThrow(() -> new MyNotFoundException("Bunday question topilmadi"))));
    }

    public ApiResponse<QuestionDto> add(QuestionDto questionDto) {
        Question question = questionMapper.toQuestion(questionDto, lessonRepository);
        return ApiResponse.success(questionMapper.toDto(questionRepository.save(question)));
    }

    public ApiResponse<QuestionDto> edit(UUID questionId, QuestionDto questionDto) {
        questionDto.setQuestionId(questionId);
        return ApiResponse.success(questionMapper.toDto(questionRepository.save(questionMapper.toQuestion(questionDto, lessonRepository))));
    }

    public ApiResponse<?> delete(UUID questionId) {
        questionRepository.deleteById(questionId);
        return ApiResponse.success("Item o'chirildi");
    }

    public ApiResponse<List<QuestionDto>> all() {
        return ApiResponse.success(questionMapper.toQuestionList(questionRepository.findAll()));
    }
}
