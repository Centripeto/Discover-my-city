package com.boomers.www.discover_my_city.core.service;

import com.boomers.www.discover_my_city.core.exception.AuthenticationException;
import com.boomers.www.discover_my_city.core.model.auth.AuthenticationRequest;
import com.boomers.www.discover_my_city.core.model.auth.AuthenticationResponse;
import com.boomers.www.discover_my_city.core.model.auth.Token;
import com.boomers.www.discover_my_city.core.model.user.Role;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.TokenRepository;
import com.boomers.www.discover_my_city.core.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AuthenticationService {
  private final JwtService jwtService;
  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;

  public AuthenticationService(
      PasswordEncoder passwordEncoder,
      JwtService jwtService,
      UserRepository userRepository,
      TokenRepository tokenRepository) {
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.userRepository = userRepository;
    this.tokenRepository = tokenRepository;
  }

  public boolean isTokenValid(String tokenJwt, User user) {
    Boolean isTokenValid =
        tokenRepository
            .findByToken(tokenJwt)
            .map(t -> !t.isExpired() && !t.isRevoked())
            .orElse(false);
    return (!Objects.isNull(user) && jwtService.isTokenValid(tokenJwt, user) && isTokenValid);
  }

  public Optional<User> extractUserFromToken(String token) {
    String username = jwtService.extractUsername(token);
    return this.userRepository.loadUserByUsername(username);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request)
      throws AuthenticationException {
    User user =
        userRepository
            .findByUsernameOrEmail(request.getUsername(), request.getUsername())
            .orElseThrow(AuthenticationException::new);
    verifyPassword(request.getPassword(), user.getPassword());
    String jwtToken = jwtService.generateToken(user);
    String refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    AuthenticationResponse response = new AuthenticationResponse(jwtToken, refreshToken);
    response.setAccessToken(jwtToken);
    response.setRefreshToken(refreshToken);
    return response;
  }

  private void verifyPassword(String requestPassword, String userPassword)
      throws AuthenticationException {
    if (!passwordEncoder.matches(requestPassword, userPassword)) {
      throw new AuthenticationException();
    }
  }

  private void saveUserToken(User user, String jwtToken) {
    Token token = new Token(jwtToken, false, false, user);
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user);
    if (validUserTokens.isEmpty()) return;
    validUserTokens.forEach(
        token -> {
          token.setExpired(true);
          token.setRevoked(true);
        });
    tokenRepository.updateAll(validUserTokens);
  }

  public AuthenticationResponse createAdmin() {
    User user =
        userRepository.save(
            User.createUser(
                "admin",
                "admin",
                "admin",
                "admin@discover.it",
                passwordEncoder.encode("admin"),
                Role.ADMIN));
    String jwtToken = jwtService.generateToken(user);
    String refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(user, jwtToken);
    return new AuthenticationResponse(jwtToken, refreshToken);
  }

  public AuthenticationResponse createContributor() {
    User user =
        userRepository.save(
            User.createUser(
                "contributor",
                "contributor",
                "contributor",
                "contributor@discover.it",
                passwordEncoder.encode("contributor"),
                Role.CONTRIBUTOR));
    String jwtToken = jwtService.generateToken(user);
    String refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(user, jwtToken);
    return new AuthenticationResponse(jwtToken, refreshToken);
  }

  public Optional<User> loadUserByUsername(String username) {
    return userRepository.loadUserByUsername(username);
  }

  public Boolean logout(String jwt) {
    Optional<User> user = extractUserFromToken(jwt);
    user.ifPresent(this::revokeAllUserTokens);
    return true;
  }
}
