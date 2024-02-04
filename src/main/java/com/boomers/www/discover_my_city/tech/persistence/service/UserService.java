package com.boomers.www.discover_my_city.tech.persistence.service;

import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.UserRepository;
import com.boomers.www.discover_my_city.tech.persistence.entity.UserEntity;
import com.boomers.www.discover_my_city.tech.persistence.repository.UserEntityRepository;
import com.boomers.www.discover_my_city.utils.mapper.UserToUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserRepository {

  private final UserEntityRepository repository;
  private final UserToUserEntity mapper;

  @Autowired
  public UserService(UserEntityRepository repository, UserToUserEntity mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Optional<User> findByUsernameOrEmail(String username, String email) {
    Optional<UserEntity> entityOpt = repository.findByUsernameOrEmail(username, email);
    return entityOpt.map(mapper::from);
  }

  // Chiamato load by username, ma consideriamo username sia username che email
  @Override
  public Optional<User> loadUserByUsername(String username) {
    return findByUsernameOrEmail(username, username);
  }

  @Override
  public User save(User user) {
    return mapper.from(repository.save(mapper.to(user)));
  }
}
