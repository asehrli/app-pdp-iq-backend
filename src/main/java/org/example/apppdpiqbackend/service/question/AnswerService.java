package org.example.apppdpiqbackend.service.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apppdpiqbackend.exception.MyNotFoundException;
import org.example.apppdpiqbackend.mapper.AnswerMapper;
import org.example.apppdpiqbackend.payload.AnswerDto;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.repository.AnswerRepository;
import org.example.apppdpiqbackend.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final QuestionRepository questionRepository;

    public ApiResponse<AnswerDto> add(AnswerDto answerDto) {
        return ApiResponse.success(answerMapper.toDto(answerRepository.save(answerMapper.toAnswer(answerDto, questionRepository))));
    }

    public ApiResponse<AnswerDto> edit(UUID answerId, AnswerDto answerDto) {
        answerDto.setAnswerId(answerId);
        return ApiResponse.success(answerMapper.toDto(answerRepository.save(answerMapper.toAnswer(answerDto, questionRepository))));
    }

    public ApiResponse<?> delete(UUID id) {
        answerRepository.deleteById(id);
        return ApiResponse.success("Ok item ochirildi");
    }

    public ApiResponse<AnswerDto> one(UUID id) {
        return ApiResponse.success(answerMapper.toDto(answerRepository.findById(id).orElseThrow(() -> new MyNotFoundException("Bunday answer topilmadi"))));
    }

    public ApiResponse<List<AnswerDto>> all() {
        return ApiResponse.success(answerMapper.toAnswersDto(answerRepository.findAll()));
    }
}
