package org.example.apppdpiqbackend.controller;

import jakarta.validation.Valid;
import org.example.apppdpiqbackend.payload.AnswerDto;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(AnswerController.ANSWER_PATH)
public interface AnswerController {
    String ANSWER_PATH = "/api/answer";

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADD_ANSWER')")
    HttpEntity<ApiResponse<AnswerDto>> add(@Valid @RequestBody AnswerDto answerDto);

    @PutMapping("/edit/{answerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('EDIT_ANSWER')")
    HttpEntity<ApiResponse<AnswerDto>> edit(@PathVariable UUID answerId, @Valid @RequestBody AnswerDto answerDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('DELETE_ANSWER')")
    HttpEntity<ApiResponse<?>> delete(@PathVariable UUID id);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GET_ANSWER')")
    HttpEntity<ApiResponse<AnswerDto>> one(@PathVariable UUID id);

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('GET_ANSWER')")
    HttpEntity<ApiResponse<List<AnswerDto>>> all();
}
