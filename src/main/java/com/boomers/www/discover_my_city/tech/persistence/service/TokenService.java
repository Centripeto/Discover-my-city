package com.boomers.www.discover_my_city.tech.persistence.service;

import com.boomers.www.discover_my_city.core.model.auth.Token;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.TokenRepository;
import com.boomers.www.discover_my_city.tech.persistence.entity.TokenEntity;
import com.boomers.www.discover_my_city.tech.persistence.entity.UserEntity;
import com.boomers.www.discover_my_city.tech.persistence.repository.TokenEntityRepository;
import com.boomers.www.discover_my_city.tech.persistence.repository.UserEntityRepository;
import com.boomers.www.discover_my_city.utils.mapper.TokenToTokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TokenService implements TokenRepository {
  private final UserEntityRepository userRepository;
  private final TokenEntityRepository repository;
  private final TokenToTokenEntity mapper;

  @Autowired
  public TokenService(
      UserEntityRepository userRepository,
      TokenEntityRepository repository,
      TokenToTokenEntity mapper) {
    this.userRepository = userRepository;
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Token save(Token token) {
    return mapper.from(repository.save(attachUserEntity(token)));
  }

  @Override
  public List<Token> findAllValidTokenByUser(User user) {
    return repository.findAllValidTokenByUser(user.getUsername()).stream()
        .map(mapper::from)
        .collect(Collectors.toList());
  }

  @Override
  public List<Token> saveAll(List<Token> tokens) {
    return repository
        .saveAll(tokens.stream().map(this::attachUserEntity).collect(Collectors.toList()))
        .stream()
        .map(mapper::from)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Token> findByToken(String token) {
    Optional<TokenEntity> tokenEntityOpt = repository.findByToken(token);
    return tokenEntityOpt.map(mapper::from);
  }

  @Override
  public List<Token> updateAll(List<Token> tokens) {
    Map<String, Token> tokenMap =
        tokens.stream().collect(Collectors.toMap(Token::getToken, Function.identity()));
    List<TokenEntity> entities =
        repository
            .findAllTokenByTokens(tokens.stream().map(Token::getToken).collect(Collectors.toList()))
            .stream()
            .map(
                entity -> {
                  Token token = tokenMap.get(entity.getToken());
                  entity.setExpired(token.isExpired());
                  entity.setRevoked(token.isRevoked());
                  return entity;
                })
            .collect(Collectors.toList());
    return repository.saveAll(entities).stream().map(mapper::from).collect(Collectors.toList());
  }

  private TokenEntity attachUserEntity(Token token) {
    TokenEntity entity = mapper.to(token);
    UserEntity user =
        userRepository
            .findByUsernameOrEmail(entity.getUser().getUsername(), entity.getUser().getEmail())
            .orElse(null);
    entity.setUser(user);
    return entity;
  }
}
