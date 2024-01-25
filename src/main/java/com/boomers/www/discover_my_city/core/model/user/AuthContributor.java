package com.boomers.www.discover_my_city.core.model.user;

import com.boomers.www.discover_my_city.core.repository.POIRepository;

public class AuthContributor extends User implements ContributorInterface {
  public AuthContributor(
      String name, String username, String lastname, String email, String password) {
    super(name, username, lastname, email, password, Role.AUTH_CONTRIBUTOR);
  }

  @Override
  public POI createPoi(POIRepository repository, POI poi) {
    poi.setStatus(Status.APPROVED);
    return repository.save(poi);
  }
}
