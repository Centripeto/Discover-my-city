package com.boomers.www.discover_my_city.utils.mapper;

import com.boomers.www.discover_my_city.core.model.auth.Token;
import com.boomers.www.discover_my_city.tech.persistence.entity.TokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenToTokenEntity implements Mapper<Token, TokenEntity> {
  private final UserToUserEntity userMapper;

  @Autowired
  public TokenToTokenEntity(UserToUserEntity userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public Token from(TokenEntity model) {
    return new Token(
        model.getToken(), model.isRevoked(), model.isExpired(), userMapper.from(model.getUser()));
  }

  @Override
  public TokenEntity to(Token model) {
    return new TokenEntity(
        model.getToken(), model.isRevoked(), model.isExpired(), userMapper.to(model.getUser()));
  }
}
