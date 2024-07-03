package com.app.Backend.exception;

public class RepartidorNotFoundException extends RuntimeException {
    public RepartidorNotFoundException(String mensaje) {
        super(mensaje);
    }
}
