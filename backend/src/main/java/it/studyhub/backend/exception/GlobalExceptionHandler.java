package it.studyhub.backend.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleUserNotFound(UserNotFoundException ex) {

        Map<String, String> error = new HashMap<>();

        error.put("message", ex.getMessage());

        return error;
    }

    @ExceptionHandler(CourseNotFoundException.class) //se viene lanciata una CourseNotFoundException, viene usato questo
    //metodo per gestirla
    @ResponseStatus(HttpStatus.NOT_FOUND) //la risposta HTTP deve avere status 404 Not Found
    public Map<String, String> handleCourseNotFound(CourseNotFoundException ex) {
        
        Map<String, String> error = new HashMap<>();
        
        error.put("message", ex.getMessage()); //mette il messaggio dell'eccezione nel json
        
        return error;
    }

}
