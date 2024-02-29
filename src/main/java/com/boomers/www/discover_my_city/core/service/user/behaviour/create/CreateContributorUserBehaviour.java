package com.boomers.www.discover_my_city.core.service.user.behaviour.create;

import com.boomers.www.discover_my_city.core.exception.AlreadyExistsException;
import com.boomers.www.discover_my_city.core.exception.NotFoundException;
import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.municipality.Municipality;
import com.boomers.www.discover_my_city.core.model.user.Role;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.service.municipality.behaviour.connectUser.ConnectUserSimpleBehaviour;
import com.boomers.www.discover_my_city.core.service.user.UserService;

import java.util.List;
import java.util.Optional;

public class CreateContributorUserBehaviour implements CreateUserBehaviour {

  private final UserService userService;
  private final User creator;

  public CreateContributorUserBehaviour(UserService userService, User creator) {
    this.userService = userService;
    this.creator = creator;
  }

  @Override
  public boolean canCreate(User user) {
    Optional<Municipality> municipality = userService.getUserMunicipality(creator);
    return municipality.isPresent()
        && List.of(Role.CONTRIBUTOR, Role.AUTH_CONTRIBUTOR).contains(user.getRole());
  }

  @Override
  public void afterCreate(User user)
      throws UnauthorizedException, AlreadyExistsException, NotFoundException {
    Municipality municipality =
        userService
            .getUserMunicipality(creator)
            .orElseThrow(() -> new UnauthorizedException("The user has no municipality connected"));
    userService.connectUserToMunicipality(
        new ConnectUserSimpleBehaviour(), user.getId(), municipality.getId());
  }
}
