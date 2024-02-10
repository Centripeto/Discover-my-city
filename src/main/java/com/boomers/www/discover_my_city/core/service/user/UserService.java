package com.boomers.www.discover_my_city.core.service.user;

import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.UserRepository;
import com.boomers.www.discover_my_city.core.service.PasswordEncoder;
import com.boomers.www.discover_my_city.core.service.user.behaviour.create.CreateUserBehaviour;

public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User createUser(CreateUserBehaviour userBehaviour, User user)
      throws UnauthorizedException {
    validate(user);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userBehaviour.create(user, userRepository);
  }

  private void validate(User user) {
    // TODO validation
  }
}
