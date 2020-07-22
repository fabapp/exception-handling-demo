package de.fabiankrueger.warehouse;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ItemNotInStockException.class, InsufficientStockException.class})
    public ResponseEntity<String> handleInsufficientStockException(Exception e) {
        // https://httpstatuses.com/409
        return handleException(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception e) {
        return handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> handleException(Exception e, HttpStatus status) {
        log.error(e.getMessage(), e);
        return new ResponseEntity(e.getMessage(), status);
    }
}
