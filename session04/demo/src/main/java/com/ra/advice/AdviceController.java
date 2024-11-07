package com.ra.advice;

import com.ra.exception.ResourceNotFoundException;
import com.ra.model.dto.errors.DataError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AdviceController {

    // Xử lý lỗi MethodArgumentNotValidException (Validation Error)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError<Map<String,String>> handlerErrorValidException(MethodArgumentNotValidException exception){
        Map<String,String> map = new HashMap<>();
        exception.getFieldErrors().forEach(fieldError -> {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return new  DataError<>(map,400);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DataError<String> handlerErrorResourceNotFoundException(ResourceNotFoundException exception){
        String error = exception.getMessage();
        return new  DataError<>(error,404);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DataError<String> handlerErrorDataExistException(NoResourceFoundException exception){
        String error = "File not found";
        return new  DataError<>(error,404);
    }
}
