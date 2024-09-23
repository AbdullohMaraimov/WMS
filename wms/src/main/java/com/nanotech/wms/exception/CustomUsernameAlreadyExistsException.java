package com.nanotech.wms.exception;

public class CustomUsernameAlreadyExistsException extends RuntimeException {
  public CustomUsernameAlreadyExistsException(String message) {
    super(message);
  }
}
