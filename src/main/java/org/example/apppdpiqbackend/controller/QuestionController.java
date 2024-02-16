package org.example.apppdpiqbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.example.apppdpiqbackend.enums.PermissionEnum;
import org.example.apppdpiqbackend.payload.ApiResponse;
import org.example.apppdpiqbackend.payload.QuestionDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequestMapping(QuestionController.QUESTION_PATH)
public interface QuestionController {
    String QUESTION_PATH = "/api/question";

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{questionId}")
    @PreAuthorize(value = "hasRole('GET_QUESTION')")
    ResponseEntity<ApiResponse<QuestionDto>> get(@PathVariable UUID questionId);


    @Operation(summary = "Admin qo'sha oladi", description = "Salom")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize(value = "hasRole('ADD_QUESTION')")
    HttpEntity<ApiResponse<QuestionDto>> add(@Valid QuestionDto questionDto);

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{questionId}/edit")
    @PreAuthorize(value = "hasRole('EDIT_QUESTION')")
    HttpEntity<ApiResponse<QuestionDto>> edit(@PathVariable UUID questionId, @Valid QuestionDto questionDto);

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{questionId}")
    @PreAuthorize(value = "hasRole('DELETE_QUESTION')")
    HttpEntity<ApiResponse<?>> delete(@PathVariable UUID questionId);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    @PreAuthorize(value = "hasRole('GET_QUESTION')")
    HttpEntity<ApiResponse<List<QuestionDto>>> getAll();
}
