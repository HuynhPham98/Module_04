package com.ra.advice;

import com.ra.exception.ResourceNotFoundException;
import com.ra.model.dto.errors.DataError;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;
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

    // Xử lý lỗi NoHandlerFoundException (404 Not Found)
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DataError<String> handleNoHandlerFoundException(NoHandlerFoundException exception) {
        String error = "Không tìm thấy tài nguyên: " + exception.getRequestURL();
        return new DataError<>(error, 404);
    }

    //Xử lý lỗi MaxUploadSizeExceededException (Upload file quá dung lượng)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError<String> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception) {
        String error = "Kích thước file tải lên vượt quá giới hạn cho phép.";
        return new DataError<>(error, 400);
    }

    // Xử lý lỗi MissingServletRequestParameterException (Thiếu tham số yêu cầu trong yêu cầu HTTP)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        String error = "Thiếu tham số yêu cầu: " + exception.getParameterName();
        return new DataError<>(error, 400);
    }

    // Xử lý lỗi MissingPathVariableException (Thiếu biến đường dẫn)
    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError<String> handleMissingPathVariableException(MissingPathVariableException exception) {
        String error = "Thiếu biến đường dẫn: " + exception.getVariableName();
        return new DataError<>(error, 400);
    }

    // Xử lý id không tồn tại http://localhost:8080/api/v1/customers/id
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DataError<String> handleResourceNotFoundException(ResourceNotFoundException exception) {
        String error = exception.getMessage();
        return new DataError<>(error, 404);
    }

    //Xử lý id khi là null http://localhost:8080/api/v1/customers/
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DataError<String> handleNoResourceFoundException(NoResourceFoundException exception) {
        String error = "File not found";
        return new DataError<>(error, 404);
    }
}
