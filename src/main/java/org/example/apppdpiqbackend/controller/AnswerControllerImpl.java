package org.example.apppdpiqbackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apppdpiqbackend.payload.AnswerDto;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.service.question.AnswerService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AnswerControllerImpl implements AnswerController {
    private final AnswerService answerService;

    @Override
    public HttpEntity<ApiResponse<AnswerDto>> add(AnswerDto answerDto) {
        return ResponseEntity.ok(answerService.add(answerDto));
    }

    @Override
    public HttpEntity<ApiResponse<AnswerDto>> edit(UUID answerId, AnswerDto answerDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(answerService.edit(answerId,answerDto));
    }

    @Override
    public HttpEntity<ApiResponse<?>> delete(UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(answerService.delete(id));
    }


    @Override
    public HttpEntity<ApiResponse<AnswerDto>> one(UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(answerService.one(id));
    }

    @Override
    public HttpEntity<ApiResponse<List<AnswerDto>>> all() {
        return ResponseEntity.status(HttpStatus.OK).body(answerService.all());
    }
}
