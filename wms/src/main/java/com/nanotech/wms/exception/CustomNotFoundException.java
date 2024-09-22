package com.nanotech.wms.exception;

public class CustomNotFoundException extends RuntimeException{
    public CustomNotFoundException(String message) {
        super(message);
    }
}
