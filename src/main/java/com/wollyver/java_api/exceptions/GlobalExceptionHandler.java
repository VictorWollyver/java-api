package com.wollyver.java_api.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFound.class)
  public ResponseEntity<Object> handleNotFound(NotFound ex) {
    Map<String, Object> body = new LinkedHashMap<>();

    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.NOT_FOUND.value());
    body.put("error", "Recurso não encontrado");
    body.put("message", ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGenericException(Exception ex) {
    Map<String, Object> body = new LinkedHashMap<>();

    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    body.put("error", "Erro interno do servidor");
    body.put("message", ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(AlreadyExists.class)
  public ResponseEntity<Object> handleAlreadyExists(Exception ex) {
    Map<String, Object> body = new LinkedHashMap<>();

    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.CONFLICT.value());
    body.put("error", "Recurso já existe");
    body.put("message", ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(Unauthorized.class)
  public ResponseEntity<Object> handleUnauthorized(Exception ex) {
    Map<String, Object> body = new LinkedHashMap<>();

    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.UNAUTHORIZED.value());
    body.put("error", "Recurso não autorizado");
    body.put("message", ex.getMessage());

    return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
  }
}
