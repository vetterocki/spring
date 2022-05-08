package com.example.cinema.exceptions.advice;

import com.example.cinema.exceptions.custom.ShowNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ShowNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ShowNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String showNotFoundHandler(ShowNotFoundException e) {
        return e.getMessage();
    }
}
