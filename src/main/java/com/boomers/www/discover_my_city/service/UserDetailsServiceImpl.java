package com.boomers.www.discover_my_city.service;

import com.boomers.www.discover_my_city.core.handler.AuthFacade;
import com.boomers.www.discover_my_city.core.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final AuthFacade authFacade;

  public UserDetailsServiceImpl(AuthFacade authFacade) {
    this.authFacade = authFacade;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user =
        authFacade
            .loadUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new UserSecurity(
        user.getPassword(), user.getUsername(), List.of(user.getRole().toString()));
  }
}
