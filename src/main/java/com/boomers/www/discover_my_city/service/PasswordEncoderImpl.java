package com.boomers.www.discover_my_city.service;

import com.boomers.www.discover_my_city.core.service.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderImpl implements PasswordEncoder {
  private final org.springframework.security.crypto.password.PasswordEncoder encoder;

  public PasswordEncoderImpl(org.springframework.security.crypto.password.PasswordEncoder encoder) {
    this.encoder = encoder;
  }

  @Override
  public String encode(CharSequence rawPassword) {
    return encoder.encode(rawPassword);
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return encoder.matches(rawPassword, encodedPassword);
  }
}
