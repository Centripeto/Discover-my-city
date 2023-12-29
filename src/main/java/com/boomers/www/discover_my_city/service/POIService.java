package com.boomers.www.discover_my_city.service;

import com.boomers.www.discover_my_city.model.POI;
import com.boomers.www.discover_my_city.model.Status;
import com.boomers.www.discover_my_city.model.User;
import com.boomers.www.discover_my_city.repository.POIRepository;

import java.util.List;

public class POIService {
  private final POIRepository poiRepository;

  public POIService(POIRepository poiRepository) {
    this.poiRepository = poiRepository;
  }

  public POI create(POI poi, User user) {
    switch (user.getRole()) {
      case CONTRIBUTOR:
        poi.setStatus(Status.IN_APPROVAL);
        break;
      case CURATOR:
      case AUTH_CONTRIBUTOR:
        poi.setStatus(Status.APPROVED);
        break;
      default:
        throw new RuntimeException("Not Authorized");
    }
    return this.poiRepository.create(poi);
  }

  public POI update(POI poi) {
    return this.poiRepository.update(poi);
  }

  public List<POI> readAll() {
    return this.poiRepository.readAll();
  }

  public POI readById(String id) {
    return this.poiRepository.readById(id);
  }
}
