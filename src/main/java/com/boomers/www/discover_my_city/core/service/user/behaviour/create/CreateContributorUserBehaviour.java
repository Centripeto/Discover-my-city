package com.boomers.www.discover_my_city.core.service.user.behaviour.create;

import com.boomers.www.discover_my_city.core.model.user.Role;
import com.boomers.www.discover_my_city.core.model.user.User;

import java.util.List;

public class CreateContributorUserBehaviour implements CreateUserBehaviour {
  @Override
  public boolean canCreate(User user) {
    return List.of(Role.CONTRIBUTOR, Role.AUTH_CONTRIBUTOR).contains(user.getRole());
  }
}
