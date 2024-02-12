package com.boomers.www.discover_my_city.service;

import com.boomers.www.discover_my_city.core.handler.AuthFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutService implements LogoutHandler {

  private final AuthFacade authFacade;

  public LogoutService(AuthFacade authFacade) {
    this.authFacade = authFacade;
  }

  @Override
  public void logout(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    response.setHeader("Access-Control-Allow-Origin", "*");
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }
    jwt = authHeader.substring(7);
    Boolean logout = authFacade.logout(jwt);
    SecurityContextHolder.clearContext();
  }
}
