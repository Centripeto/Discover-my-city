package com.boomers.www.discover_my_city.core.service;

import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.user.ContributorInterface;
import com.boomers.www.discover_my_city.core.model.user.POI;
import com.boomers.www.discover_my_city.core.model.user.Role;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.POIRepository;

import java.util.Arrays;
import java.util.List;

public class ContributorService {
  private final List<Role> CREATION_ENABLED_ROLES =
      Arrays.asList(Role.CONTRIBUTOR, Role.AUTH_CONTRIBUTOR);
  private final POIRepository poiRepository;

  public ContributorService(POIRepository poiRepository) {
    this.poiRepository = poiRepository;
  }

  public POI createPOI(User user, POI poi) throws UnauthorizedException {
    if (!CREATION_ENABLED_ROLES.contains(user.getRole())) {
      throw new UnauthorizedException("The user is not enabled to create POI");
    }
    return ((ContributorInterface) user).createPoi(poiRepository, poi);
  }
}
