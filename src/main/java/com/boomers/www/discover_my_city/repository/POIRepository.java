package com.boomers.www.discover_my_city.repository;

import com.boomers.www.discover_my_city.model.POI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class POIRepository {
  private final List<POI> pois;

  public POIRepository() {
    this.pois = new ArrayList<>();
  }

  public POI create(POI poi) {
    this.pois.add(poi);
    return poi;
  }

  public POI update(POI poi) {
    int index = -1;
    for (int i = 0; i < pois.size(); i++) {
      if (pois.get(i).getId().equals(poi.getId())) {
        index = i;
        break;
      }
    }
    if (index < 0) {
      throw new RuntimeException("POI not found!");
    }
    pois.set(index, poi);
    return poi;
  }

  public List<POI> readAll() {
    return this.pois;
  }

  public POI readById(String id) {
    Optional<POI> poi = this.pois.stream().filter(p -> p.getId().equals(id)).findFirst();
    if (poi.isEmpty()) {
      throw new RuntimeException("POI not found!");
    }
    return poi.get();
  }
}
