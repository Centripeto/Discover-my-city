package com.boomers.www.discover_my_city.utils.mapper;

import com.boomers.www.discover_my_city.core.model.user.Role;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.tech.persistence.entity.UserEntity;
import com.boomers.www.discover_my_city.tech.persistence.entity.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserToUserEntity implements Mapper<User, UserEntity> {
  @Override
  public User from(UserEntity model) {
    String name = model.getName();
    String username = model.getUsername();
    String email = model.getEmail();
    String lastname = model.getLastname();
    String password = model.getPassword();
    Role role = Role.valueOf(model.getRole().toString());
    return new User(name, username, lastname, email, password, role);
  }

  @Override
  public UserEntity to(User model) {
    UserEntity entity = new UserEntity();
    entity.setName(model.getName());
    entity.setUsername(model.getName());
    entity.setEmail(model.getEmail());
    entity.setLastname(model.getLastname());
    entity.setPassword(model.getPassword());
    entity.setRole(UserRole.valueOf(model.getRole().toString()));
    return entity;
  }
}
