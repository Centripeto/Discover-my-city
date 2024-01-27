package com.boomers.www.discover_my_city.core.service.poi;

import com.boomers.www.discover_my_city.api.dto.Paged;
import com.boomers.www.discover_my_city.core.exception.UnauthorizedException;
import com.boomers.www.discover_my_city.core.model.poi.POI;
import com.boomers.www.discover_my_city.core.model.poi.POIRequest;
import com.boomers.www.discover_my_city.core.repository.POIRepository;
import com.boomers.www.discover_my_city.core.service.poi.behaviour.create.CreatePoiBehaviour;
import com.boomers.www.discover_my_city.core.service.poi.behaviour.list.ListPoiBehaviour;

public class POIService {
  private final POIRepository poiRepository;

  public POIService(POIRepository poiRepository) {
    this.poiRepository = poiRepository;
  }

  public POI createPOI(CreatePoiBehaviour createPoiBehaviour, POI poi)
      throws UnauthorizedException {
    return createPoiBehaviour.createPoi(poi, poiRepository);
  }

  public Paged<POI> findPois(ListPoiBehaviour listPoiStrategy, POIRequest request) {
    return listPoiStrategy.find(request, poiRepository);
  }
}
