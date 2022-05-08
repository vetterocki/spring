package com.example.cinema.exceptions.advice;

import com.example.cinema.exceptions.custom.InvalidParamsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidParamAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidParamsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidParamHandler(InvalidParamsException e) {
        return e.getMessage();
    }
}
