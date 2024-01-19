package com.delivery.cloudeats.domain.exception;

public abstract class UEntityNotFoundException extends BusinessException {
    public UEntityNotFoundException(String message) {
        super(message);
    }
}
