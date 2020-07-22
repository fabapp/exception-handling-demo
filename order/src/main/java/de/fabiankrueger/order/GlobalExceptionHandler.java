package de.fabiankrueger.order;

import brave.Tracer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final Tracer tracer;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDescription> handleAllExceptions(Exception e) {
        log.error(e.getMessage(), e);
        final String traceId = tracer.currentSpan().context().traceIdString();
        return new ResponseEntity(new ErrorDescription(e.getMessage(), traceId), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

@Getter
@Setter
@AllArgsConstructor
class ErrorDescription {
    private String message;
    private String traceId;
}
