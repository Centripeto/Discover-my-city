package com.boomers.www.discover_my_city.core.exception;

public class UnauthorizedException extends Throwable {
  public UnauthorizedException() {
    super("You are not authorized");
  }

  public UnauthorizedException(String message) {
    super(message);
  }
}
