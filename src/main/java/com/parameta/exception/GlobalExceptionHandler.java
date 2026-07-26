package com.parameta.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.parameta.dto.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(BusinessException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ErrorResponse manejarBusinessException(
                BusinessException ex) {

                        return ErrorResponse.builder()
                                .timestamp(LocalDateTime.now())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .mensaje(ex.getMessage())
                                .build();
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ErrorResponse manejarRecursoNoEncontrado(
                        ResourceNotFoundException ex) {

                return ErrorResponse.builder()
                                .timestamp(LocalDateTime.now())
                                .status(HttpStatus.NOT_FOUND.value())
                                .mensaje(ex.getMessage())
                                .build();
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ErrorResponse manejarValidaciones(
                        MethodArgumentNotValidException ex) {

                Map<String, String> errores = new HashMap<>();

                ex.getBindingResult()
                                .getFieldErrors()
                                .forEach(error -> errores.put(
                                                error.getField(),
                                                error.getDefaultMessage()));

                return ErrorResponse.builder()
                                .timestamp(LocalDateTime.now())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .mensaje("Error de validación")
                                .errores(errores)
                                .build();
        }
}