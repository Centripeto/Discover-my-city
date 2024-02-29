package com.boomers.www.discover_my_city.core.service.user.behaviour.create;

import com.boomers.www.discover_my_city.core.exception.AlreadyExistsException;
import com.boomers.www.discover_my_city.core.exception.NotFoundException;
import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.UserRepository;

public interface CreateUserBehaviour {

  default User create(User user, UserRepository poiRepository)
      throws UnauthorizedException, AlreadyExistsException, NotFoundException {
    if (!canCreate(user)) {
      throw new UnauthorizedException();
    }
    User savedUser = poiRepository.save(user);
    afterCreate(savedUser);
    return savedUser;
  }

  default void afterCreate(User user)
      throws UnauthorizedException, AlreadyExistsException, NotFoundException {}

  boolean canCreate(User user);
}
