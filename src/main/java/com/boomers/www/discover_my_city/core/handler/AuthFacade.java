package com.boomers.www.discover_my_city.core.handler;

import com.boomers.www.discover_my_city.core.exception.AuthenticationException;
import com.boomers.www.discover_my_city.core.model.auth.AuthenticationRequest;
import com.boomers.www.discover_my_city.core.model.auth.AuthenticationResponse;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.TokenRepository;
import com.boomers.www.discover_my_city.core.repository.UserRepository;
import com.boomers.www.discover_my_city.core.service.AuthenticationService;
import com.boomers.www.discover_my_city.core.service.JwtConfig;
import com.boomers.www.discover_my_city.core.service.JwtService;
import com.boomers.www.discover_my_city.core.service.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthFacade {

  private final AuthenticationService authenticationService;

  @Autowired
  public AuthFacade(
      JwtConfig jwtConfig,
      PasswordEncoder passwordEncoder,
      UserRepository userRepository,
      TokenRepository tokenRepository) {
    JwtService jwtService =
        new JwtService(
            jwtConfig.getSecretKey(),
            jwtConfig.getJwtExpiration(),
            jwtConfig.getRefreshExpiration());
    this.authenticationService =
        new AuthenticationService(passwordEncoder, jwtService, userRepository, tokenRepository);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request)
      throws AuthenticationException {
    return this.authenticationService.authenticate(request);
  }

  public AuthenticationResponse registerAdmin() {
    authenticationService.createAuthContributor();
    authenticationService.createAdmin();
    return authenticationService.createContributor();
  }

  public Optional<User> loadUserByUsername(String username) {
    return authenticationService.loadUserByUsername(username);
  }

  public Optional<User> extractUserFromToken(String token) {
    return authenticationService.extractUserFromToken(token);
  }

  public boolean isTokenValid(String token) {
    User user = extractUserFromToken(token).orElse(null);
    return authenticationService.isTokenValid(token, user);
  }

  public Boolean logout(String jwt) {
    return authenticationService.logout(jwt);
  }
}
