package org.example.apppdpiqbackend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

/*@ControllerAdvice
@ResponseBody*/
@RestControllerAdvice // ControllerAdvice va ResponseBody anotatsiyalari birgalikda RestController anotatsiyasini beradi
public class GlobalExceptionHandler{
    @ExceptionHandler(MyNotFoundException.class)
    public ResponseEntity<ErrorDTO> error_404(MyNotFoundException e, HttpServletRequest req) {
        return ResponseEntity.status(404)
                .body(ErrorDTO.builder()
                        .errorPath(req.getRequestURI())
                        .errorCode(404)
                        .errorBody(e.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> error_404(MethodArgumentNotValidException e, HttpServletRequest req) {
        Map<String, List<String>> errorMessage = new HashMap<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            String field = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();
            errorMessage.compute(field, (s, strings) -> {
                strings = Objects.requireNonNullElse(strings, new ArrayList<>());
                strings.add(defaultMessage);
                return strings;
            });
        }
        return ResponseEntity.status(404)
                .body(ErrorDTO.builder()
                        .errorPath(req.getRequestURI())
                        .errorCode(404)
                        .errorBody(errorMessage)
                        .build());
    }
}
