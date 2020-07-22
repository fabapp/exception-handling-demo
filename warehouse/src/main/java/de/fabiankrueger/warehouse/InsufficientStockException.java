package de.fabiankrueger.warehouse;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String s) {
        super(s);
    }
}
