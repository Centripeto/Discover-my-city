package com.boomers.www.discover_my_city;

import com.boomers.www.discover_my_city.model.POI;
import com.boomers.www.discover_my_city.model.Status;
import com.boomers.www.discover_my_city.service.POIService;

public class Curatore {

  private final POIService poiService;

  public Curatore(POIService poiService) {
    this.poiService = poiService;
  }

  public POI approve(POI poi) {
    poi.setStatus(Status.APPROVED);
    return this.poiService.update(poi);
  }
}
