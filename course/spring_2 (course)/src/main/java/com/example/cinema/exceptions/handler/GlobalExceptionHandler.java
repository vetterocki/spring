package com.example.cinema.exceptions.handler;

import com.example.cinema.exceptions.authorization.UserAlreadyExistsException;
import com.example.cinema.exceptions.authorization.UserNotFoundException;
import com.example.cinema.exceptions.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> getWrongUser(UserAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<?> seatNotFoundHandler(SeatNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ShowNotFoundException.class)
    public ResponseEntity<?> showNotFoundHandler(ShowNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<?> ticketNotFoundHandler(TicketNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(SeatAlreadyReservedException.class)
    public ResponseEntity<?> seatAlreadyReservedHandler(SeatAlreadyReservedException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundHandler(UserNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(IllegalLocalDateTimeException.class)
    public ResponseEntity<?> illegalLocalDateTimeHandler(IllegalLocalDateTimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<?> globalErrorHandler(RuntimeException e) {
        return ResponseEntity.badRequest().build();
    }


}
