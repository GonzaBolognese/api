package com.nocountry.api.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nocountry.api.dto.ApiError;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(Exception exception, HttpServletRequest request){

        ApiError apiError = new ApiError();

        apiError.setBackendMessage(exception.getMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setTime(LocalDateTime.now());
        apiError.setMessage("Error interno en el servidor, vuelva a intentarlo");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                     HttpServletRequest request){
        ApiError apiError = new ApiError();

        apiError.setBackendMessage(exception.getMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setTime(LocalDateTime.now());
        apiError.setMessage("Error en la petición enviada");
        

        List<String> subsMessages = new ArrayList<>(exception.getAllErrors().stream().map(each -> each.getDefaultMessage()).collect(Collectors.toList()));

        apiError.setSubsMessages(subsMessages);
        

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);

    }

}