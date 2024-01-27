package com.boomers.www.discover_my_city.core.handler;

import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.core.model.user.User;
import com.boomers.www.discover_my_city.core.repository.POIRepository;
import com.boomers.www.discover_my_city.core.service.poi.POIService;
import com.boomers.www.discover_my_city.core.service.poi.behaviour.create.CreateApprovedPoiBehaviour;
import com.boomers.www.discover_my_city.core.service.poi.behaviour.create.CreateInApprovalPoiBehaviour;
import com.boomers.www.discover_my_city.core.service.poi.behaviour.create.CreatePoiBehaviour;
import com.boomers.www.discover_my_city.core.service.poi.behaviour.create.NotAuthorizedCreatePoiBehaviour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PoiFacade {

  private final POIService poiService;

  @Autowired
  public PoiFacade(POIRepository poiRepository) {
    this.poiService = new POIService(poiRepository);
  }

  public POI createPoi(User user, POI poi) throws UnauthorizedException {
    return poiService.createPOI(getStrategy(user), poi);
  }

  private CreatePoiBehaviour getStrategy(User user) {
    return switch (user.getRole()) {
      case CONTRIBUTOR -> new CreateInApprovalPoiBehaviour(user);
      case CURATORE, AUTH_CONTRIBUTOR -> new CreateApprovedPoiBehaviour(user);
      case ADMIN -> new NotAuthorizedCreatePoiBehaviour();
    };
  }
}
