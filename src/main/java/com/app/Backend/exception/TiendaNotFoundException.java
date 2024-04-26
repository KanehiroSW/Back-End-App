package com.app.Backend.exception;

public class TiendaNotFoundException extends RuntimeException {
    public TiendaNotFoundException(String mensaje) {
        super(mensaje);
    }
}

