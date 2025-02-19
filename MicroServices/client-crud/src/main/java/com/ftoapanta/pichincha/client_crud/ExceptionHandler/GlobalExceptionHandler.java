package com.ftoapanta.pichincha.client_crud.ExceptionHandler;
import com.ftoapanta.pichincha.client_crud.dto.BaseResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(BaseResponseDTO.builder()
                .success(false)
                .data(errors)
                .message(ex.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<BaseResponseDTO> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        Map<String, String> errors = new HashMap<>();
        String fieldName = ex.getName();
        String requiredType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconocido";
        String message = String.format("El valor '%s' no es válido para el parámetro '%s'. Se esperaba un valor de tipo %s.",
                ex.getValue(), fieldName, requiredType);
        errors.put(fieldName, message);
        return new ResponseEntity<>(BaseResponseDTO.builder()
                .success(false)
                .data(errors)
                .message(ex.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<BaseResponseDTO> handleNoSuchElementException(NoSuchElementException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return new ResponseEntity<>(BaseResponseDTO.builder()
                .success(false)
                .data(errors)
                .message(ex.getMessage())
                .build(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponseDTO> handleGenericException(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Ocurrió un error interno en el servidor.");

        return new ResponseEntity<>(BaseResponseDTO.builder()
                .success(false)
                .data(errors)
                .message(ex.getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
