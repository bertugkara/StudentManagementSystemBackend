package com.tubitakyte.studentmanagementsystem.common.ExceptionHandlers;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public List<String> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        return exception.getFieldErrors().stream().map(field -> field.getDefaultMessage()).toList();
    }


    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public String handleMethodArgumentException(UsernameNotFoundException exception) {
        return exception.getCause().getMessage();
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public String handleMethodArgumentException(EntityNotFoundException exception) {
        return exception.getCause().getMessage();
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public String handleMethodArgumentException(RuntimeException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = {IOException.class})
    public String handleMethodArgumentException(IOException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(MaxUploadSizeExceededException exception) {
        return "File is too Large!";
    }

   /* @ExceptionHandler(MissingServletRequestPartException.class)
    public String handleMaxSizeException(MissingServletRequestPartException exception) {
        return "Bad Request!";
    }
*/
}
