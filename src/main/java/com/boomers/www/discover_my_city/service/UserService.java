package com.boomers.www.discover_my_city.service;

import com.boomers.www.discover_my_city.model.User;
import com.boomers.www.discover_my_city.repository.UserRepository;

import java.util.List;

public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> readAll() {
    return this.userRepository.readAll();
  }

  public User create(User toCreate, User user) {
    switch (user.getRole()) {
      case ADMIN:
        return this.userRepository.create(toCreate);
      default:
        throw new RuntimeException("Not authorized");
    }
  }
}
