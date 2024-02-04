package com.boomers.www.discover_my_city.core.service;

public interface PasswordEncoder {
  String encode(CharSequence rawPassword);

  boolean matches(CharSequence rawPassword, String encodedPassword);
}
