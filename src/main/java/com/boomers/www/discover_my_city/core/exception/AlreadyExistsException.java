package com.boomers.www.discover_my_city.core.exception;

public class AlreadyExistsException extends Throwable {
  public AlreadyExistsException() {
    super("Already exists");
  }

  public AlreadyExistsException(String message) {
    super(message);
  }
}
