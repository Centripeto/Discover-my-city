package com.boomers.www.discover_my_city.core.repository;

import com.boomers.www.discover_my_city.core.model.user.User;

import java.util.Optional;

public interface UserRepository {
  Optional<User> findByUsernameOrEmail(String username, String email);

  Optional<User> loadUserByUsername(String username);

  User save(User user);
}
