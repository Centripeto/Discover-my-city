package com.boomers.www.discover_my_city.core.service.poi.behaviour.create;

import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.core.repository.POIRepository;

public class NotAuthorizedCreatePoiBehaviour implements CreatePoiBehaviour {

  @Override
  public POI createPoi(POI poi, POIRepository poiRepository) throws UnauthorizedException {
    throw new UnauthorizedException();
  }
}
