package com.mlorenzo.customerservice.controllers;

import com.mlorenzo.customerservice.dtos.ErrorResponseDto;
import com.mlorenzo.customerservice.exceptions.CustomerAlreadyExistsException;
import com.mlorenzo.customerservice.exceptions.CustomerNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

// Con esta anotación, ocultamos esta clase a OpenAPI para que no aparezca en la documentación generada la respuesta
// 404 en todos los endpoints del controlador "AccountController" debido al uso de la anotación
// @ResponseStatus(HttpStatus.NOT_FOUND) en esta clase.
@Hidden
@ControllerAdvice
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        String errorMessage = ex.getAllErrors().stream()
                // Versión simplificada de la expresión "err -> err.getDefaultMessage()"
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(request.getDescription(false),
                errorMessage, LocalDateTime.now());
        return ResponseEntity.badRequest().body(errorResponseDto);
    }

    // Alternativa equivalente a extender de la clase "ResponseEntityExceptionHandler" y sobrescribir su método
    // "handleMethodArgumentNotValid".
    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                                  WebRequest webRequest) {
        String errorMessage = ex.getAllErrors().stream()
                // Versión simplificada de la expresión "err -> err.getDefaultMessage()"
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
                errorMessage, LocalDateTime.now());
        return ResponseEntity.badRequest().body(errorResponseDto);
    }*/

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleAccountAlreadyExistsException(CustomerAlreadyExistsException ex,
                                                                                WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
                ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(errorResponseDto);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(CustomerNotFoundException.class)
    public ErrorResponseDto handleAccountNotFoundException(CustomerNotFoundException ex, WebRequest webRequest) {
        return new ErrorResponseDto(webRequest.getDescription(false),
                ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
                ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.internalServerError().body(errorResponseDto);
    }
}
