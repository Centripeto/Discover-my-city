package com.boomers.www.discover_my_city.core.service.user.behaviour.create;

import com.boomers.www.discover_my_city.core.model.user.User;

public class CreateNotAuthorizedUserBehaviour implements CreateUserBehaviour {
  @Override
  public boolean canCreate(User user) {
    return false;
  }
}
