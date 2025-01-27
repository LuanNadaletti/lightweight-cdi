package com.nadaletti.impl.exception;

public class DependencyInjectionException extends RuntimeException {

    public DependencyInjectionException(String message) {
        super(message);
    }

    public DependencyInjectionException(String message, Throwable cause) {
        super(message, cause);
    }

}
