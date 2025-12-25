package com.actors.handler;

import com.actors.exception.DataNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerHandler {

    private static final Logger LOGGER = Logger.getLogger(ControllerHandler.class.getName());

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Err handleActorNotFoundException(DataNotFoundException e, HttpServletRequest request) {
        LOGGER.log(Level.WARNING, "Not found", e);
        return new Err(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not found",
                e.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(ProfileValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Err handleProfileValidationException(ProfileValidationException e, HttpServletRequest request) {
        LOGGER.log(Level.WARNING, "Profile validation failed", e);
        return new Err(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad request",
                e.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(ActorValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Err handleValidationException(ActorValidationException e, HttpServletRequest request) {
        LOGGER.log(Level.SEVERE, "Actor validation failed", e);
        return new Err(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad request",
                e.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Err handleException(MethodArgumentNotValidException e, HttpServletRequest request) {
        LOGGER.log(Level.WARNING, "Exception", e);
        String errorMsg = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return new Err(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation error",
                errorMsg,
                request.getRequestURI()
        );
    }

    @ExceptionHandler({ChangeSetPersister.NotFoundException.class, EmptyResultDataAccessException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Err handleNotFound(DataNotFoundException e, HttpServletRequest request) {
        LOGGER.log(Level.WARNING, "Not found", e);
        return new Err(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not found",
                e.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Err handleDatabaseException(DataAccessException ex, HttpServletRequest request) {
        LOGGER.log(Level.WARNING, "Database error", ex);
        return new Err(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Database Error",
                "Произошла ошибка при работе с базой данных",
                request.getRequestURI()
        );
    }
}
