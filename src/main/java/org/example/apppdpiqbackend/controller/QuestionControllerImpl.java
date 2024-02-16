package org.example.apppdpiqbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.QuestionDto;
import org.example.apppdpiqbackend.service.question.QuestionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class QuestionControllerImpl implements QuestionController {
    private final QuestionService questionService;

    @Override
    public ResponseEntity<ApiResponse<QuestionDto>> get(UUID questionId) {
        return ResponseEntity.ok(questionService.one(questionId));
    }

    @Override
    public HttpEntity<ApiResponse<QuestionDto>> add(QuestionDto questionDto) {
        return ResponseEntity.ok(questionService.add(questionDto));
    }

    @Override
    public HttpEntity<ApiResponse<QuestionDto>> edit(UUID questionId, QuestionDto questionDto) {
        return ResponseEntity.ok(questionService.edit(questionId, questionDto));
    }

    @Override
    public HttpEntity<ApiResponse<?>> delete(UUID questionId) {
        return ResponseEntity.ok(questionService.delete(questionId));
    }

    @Override
    public HttpEntity<ApiResponse<List<QuestionDto>>> getAll() {
        return ResponseEntity.ok(questionService.all());
    }
}
