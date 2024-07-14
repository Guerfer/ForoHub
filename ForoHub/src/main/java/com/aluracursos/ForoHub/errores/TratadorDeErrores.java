package com.aluracursos.ForoHub.errores;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class TratadorDeErrores {

    ///// este tratador de error se ejecuta cuando se ingresa un ID no registrado en la base de datos /////
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> errorHandlerRecursoNoEncontrado(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    ///// este tratador de error se ejecuta cuando se ingresa valor que no corresponde a un ID valido /////
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, WebRequest request) {
        Throwable mostSpecificCause = e.getMostSpecificCause();
        if (mostSpecificCause instanceof InvalidFormatException) {
            String errorMessage = "El ID proporcionado no es válido. Debe ser un número, favor validar nuevamente.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La solicitud no es legible");
    }


    ///// este tratador de error se ejecuta cuando se actualiza una informacion pero el campo ID esta vacio (NULL) /////
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    ///// este tratador de error se ejecuta cuando se ingresa un ID no registrado en la base de datos /////
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Tópico no se encuentra registrado con este ID");
    }

    ///// este tratador de error se ejecuta cuando hay errores de validación en los argumentos /////
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    ///// este tratador de error se ejecuta cuando se ingresa un valor que no se un numero en ID /////
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        String errorMessage = "El valor que proporcionaste como ID no es valido, ya que debe ser un numero.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

}
