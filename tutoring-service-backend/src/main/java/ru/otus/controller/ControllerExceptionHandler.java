package ru.otus.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity noSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
    }
}