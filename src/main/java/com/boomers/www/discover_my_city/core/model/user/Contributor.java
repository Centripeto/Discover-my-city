package com.boomers.www.discover_my_city.core.model.user;

import com.boomers.www.discover_my_city.core.repository.POIRepository;

public class Contributor extends User implements ContributorInterface {
  public Contributor(String name, String username, String lastname, String email, String password) {
    super(name, username, lastname, email, password, Role.CONTRIBUTOR);
  }

  @Override
  public POI createPoi(POIRepository repository, POI poi) {
    poi.setStatus(Status.IN_APPROVAL);
    return repository.save(poi);
  }
}
