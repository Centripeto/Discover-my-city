package com.boomers.www.discover_my_city.core.exception;

public class NotFoundException extends Throwable {
  public NotFoundException() {
    super("Element not found");
  }

  public NotFoundException(String message) {
    super(message);
  }
}
