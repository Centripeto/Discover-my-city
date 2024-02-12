package com.boomers.www.discover_my_city.core.service.user.behaviour.create;

import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.UserRepository;

public interface CreateUserBehaviour {

  default User create(User user, UserRepository poiRepository) throws UnauthorizedException {
    if (!canCreate(user)) {
      throw new UnauthorizedException();
    }
    return poiRepository.save(user);
  }

  boolean canCreate(User user);
}
