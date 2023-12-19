package com.boomers.www.discover_my_city.service;

import com.boomers.www.discover_my_city.model.POI;
import com.boomers.www.discover_my_city.repository.POIRepository;

import java.util.List;

public class POIService {
  private final POIRepository poiRepository;

  public POIService(POIRepository poiRepository) {
    this.poiRepository = poiRepository;
  }

  public POI create(POI poi) {
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
