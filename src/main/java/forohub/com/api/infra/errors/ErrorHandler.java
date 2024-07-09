package forohub.com.api.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDataValidation>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors().stream().map(ErrorDataValidation::new).toList();

        return ResponseEntity.badRequest().body(
                errors
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ErrorDataValidation>> handleConstraintViolationException(ConstraintViolationException ex) {
        var errors = new ErrorDataValidation(ex.getConstraintViolations().toString(), ex.getMessage());
        return ResponseEntity.badRequest().body(Collections.singletonList(errors));
    }

    public record ErrorDataValidation(String field, String error) {
        public ErrorDataValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
