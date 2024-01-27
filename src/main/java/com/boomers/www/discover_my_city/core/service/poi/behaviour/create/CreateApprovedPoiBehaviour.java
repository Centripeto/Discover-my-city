package com.boomers.www.discover_my_city.core.service.poi.behaviour.create;

import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.core.model.user.Status;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.POIRepository;

public class CreateApprovedPoiBehaviour implements CreatePoiBehaviour {

  private final User user;

  public CreateApprovedPoiBehaviour(User user) {
    this.user = user;
  }

  @Override
  public POI createPoi(POI poi, POIRepository poiRepository) throws UnauthorizedException {
    poi.setStatus(Status.APPROVED);
    poi.setCreator(user);
    poi.setApprover(user);
    return poiRepository.save(poi);
  }
}
