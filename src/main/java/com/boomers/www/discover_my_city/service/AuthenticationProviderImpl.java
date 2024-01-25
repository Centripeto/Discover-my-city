package com.boomers.www.discover_my_city.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderImpl extends DaoAuthenticationProvider {
  @Autowired
  public AuthenticationProviderImpl(
      UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
    super();
    this.setUserDetailsService(userDetailsService);
    this.setPasswordEncoder(passwordEncoder);
  }
}
