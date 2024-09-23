package com.nanotech.wms.exception;

public class CustomInsufficientProductException extends RuntimeException {
    public CustomInsufficientProductException(String message) {
        super(message);
    }
}
