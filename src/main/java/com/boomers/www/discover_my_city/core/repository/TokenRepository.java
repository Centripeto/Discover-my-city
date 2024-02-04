package com.boomers.www.discover_my_city.core.repository;

import com.boomers.www.discover_my_city.core.model.auth.Token;
import com.boomers.www.discover_my_city.core.model.user.User;

import java.util.List;
import java.util.Optional;

public interface TokenRepository {
  Token save(Token token);

  List<Token> findAllValidTokenByUser(User user);

  List<Token> saveAll(List<Token> tokens);

  Optional<Token> findByToken(String token);

  List<Token> updateAll(List<Token> validUserTokens);
}
