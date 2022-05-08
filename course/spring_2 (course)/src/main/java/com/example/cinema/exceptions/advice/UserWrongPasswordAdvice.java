package com.example.cinema.exceptions.advice;

import com.example.cinema.exceptions.custom.UserWrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserWrongPasswordAdvice {

    @ResponseBody
    @ExceptionHandler(UserWrongPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String showWrongPassword(UserWrongPasswordException e) {
        return e.getMessage();
    }
}
