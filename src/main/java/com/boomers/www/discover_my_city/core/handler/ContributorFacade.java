package com.boomers.www.discover_my_city.core.handler;

import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.user.POI;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.POIRepository;
import com.boomers.www.discover_my_city.core.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContributorFacade {

  private final ContributorService contributorService;

  @Autowired
  public ContributorFacade(POIRepository poiRepository) {
    contributorService = new ContributorService(poiRepository);
  }

  public POI createPoi(User user, POI poi) throws UnauthorizedException {
    return contributorService.createPOI(user, poi);
  }
}
