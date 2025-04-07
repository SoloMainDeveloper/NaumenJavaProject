package ru.solomein_michael.NauJava.controller.advice;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.solomein_michael.NauJava.dto.ErrorDto;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(java.lang.Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto exception(java.lang.Exception e) {
        return ErrorDto.create(e);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto exception(ResourceNotFoundException e) {
        return ErrorDto.create(e);
    }
}